import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Hibernate;
import org.primefaces.PrimeFaces;

import model.Pedido;
import model.Repartidor;

public class ActualizarPedidoBean {
	private int idSucursal;
	private Pedido pedido;
	private DActualizarPedidoBean delegado;
	private DelegadoBean delegado2;
	private List<Pedido> pedidos;
	private List<Pedido> selectedPedidos;
	private Pedido selectedPedido;
	private String radioButtonValue;

	private Repartidor repartidor;
	private List<Repartidor> repartidoresDisponibles;
	private int repartidorComboBox;

	public ActualizarPedidoBean() {
		// TODO Auto-generated constructor stub
		super();
		this.idSucursal = 28;
		this.pedido = new Pedido();
		pedidos = new ArrayList<Pedido>();
		delegado = new DActualizarPedidoBean();
		selectedPedido = new Pedido();
		selectedPedidos = new ArrayList<Pedido>();
		repartidoresDisponibles = new ArrayList<Repartidor>();
		repartidor = new Repartidor();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public DActualizarPedidoBean getDelegado() {
		return delegado;
	}

	public void setDelegado(DActualizarPedidoBean delegado) {
		this.delegado = delegado;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public List<Pedido> getSelectedPedidos() {
		return selectedPedidos;
	}

	public void setSelectedPedidos(List<Pedido> selectedPedidos) {
		this.selectedPedidos = selectedPedidos;
	}

	public Pedido getSelectedPedido() {
		return selectedPedido;
	}

	public void setSelectedPedido(Pedido selectedPedido) {
		this.selectedPedido = selectedPedido;
	}

	public String getRadioButtonValue() {
		return radioButtonValue;
	}

	public void setRadioButtonValue(String redioButtonValue) {
		this.radioButtonValue = redioButtonValue;
	}

	public Repartidor getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public List<Repartidor> getRepartidoresDisponibles() {
		return repartidoresDisponibles;
	}

	public void setRepartidoresDisponibles(List<Repartidor> repartidoresDisponibles) {
		this.repartidoresDisponibles = repartidoresDisponibles;
	}

	public int getRepartidorComboBox() {
		return repartidorComboBox;
	}

	public void setRepartidorComboBox(int repartidorComboBox) {
		this.repartidorComboBox = repartidorComboBox;
	}

	public DelegadoBean getDelegado2() {
		return delegado2;
	}

	public void setDelegado2(DelegadoBean delegado2) {
		this.delegado2 = delegado2;
	}

	public String cargarPedidos() {
		this.idSucursal = delegado2.getUserFound().getSucursals().get(0).getIdSucursal();
		System.out.println("cargando Pedidos de la sucursal ID: " + this.idSucursal);
		pedidos = delegado.buscarPedidosSucursal(idSucursal);
		System.out.println("Cantidad pedidos sucursal: " + pedidos.size());
		repartidor = new Repartidor();
		this.cargarRepartidores();

		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
	}

	public void cargarRepartidores() {
		repartidoresDisponibles = delegado.buscarRepartidores();
		System.out.println("Nombre del primer repartidor: " + repartidoresDisponibles.get(0).getNombreRepartidor());
	}

	public boolean hasSelectedPedidos() {
		return this.selectedPedidos != null && !this.selectedPedidos.isEmpty();
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedPedidos()) {
			int size = this.selectedPedidos.size();
			return size > 1 ? size + " Pedidos seleccionados" : "Un pedido seleccionado";
		}
		return "Eliminar";
	}

	public void savePedido() {
		// Logica para actualizar pedidos
		int nuevoValorPedido = -1;
		nuevoValorPedido = Integer.parseInt(radioButtonValue);
		
		//Preparar pedido
		
		this.selectedPedido.setEstado(nuevoValorPedido);
		this.selectedPedido.setRepartidor(this.repartidor);
		this.selectedPedido.setProductos(null);
		this.selectedPedido.setSucursal(null);
		this.selectedPedido.setUsuario(null);
		
		for (Repartidor iter : this.repartidoresDisponibles) {
			if (iter.getIdRepartidor() == this.repartidorComboBox) {
				System.out.println("Repartidor asociado");
				this.repartidor = iter;
			}
		}

		if (this.repartidor != null) {
			this.selectedPedido.setRepartidor(this.repartidor);
			System.out.println("Se esta tratando de asignar un repartidor");
			this.saveRepartidor();
			System.out.println("Se le asigno un repartidor al pedido");
		} else
			this.selectedPedido.setRepartidor(null);

		delegado.updatePedido(this.selectedPedido);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Estado del pedido actualizado correctamente"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-pedidos");
	}

	public void saveRepartidor() {
		Hibernate.initialize(repartidor.getPedidos());
		System.out.println("Nombre repartidor: " + this.repartidor.getNombreRepartidor());

		if (this.repartidor.getPedidos() != null) {
			this.repartidor.setPedidos(new ArrayList<Pedido>());
		}
		this.repartidor.getPedidos().add(selectedPedido);
		this.repartidor.setSucursal(null);
		this.delegado.updateRepartidor(repartidor);
		System.out.println("Se actualizo al repartidor");
		this.repartidor = new Repartidor();
	}

}

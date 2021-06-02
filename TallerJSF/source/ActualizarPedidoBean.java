import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.Pedido;

public class ActualizarPedidoBean {
	private int idSucursal;
	private Pedido pedido;
	private DActualizarPedidoBean delegado;
	private List<Pedido> pedidos;
	private List<Pedido> selectedPedidos;
	private Pedido selectedPedido;
	private String radioButtonValue;

	public ActualizarPedidoBean() {
		// TODO Auto-generated constructor stub
		super();
		this.idSucursal = 28;
		this.pedido = new Pedido();
		pedidos = new ArrayList<Pedido>();
		delegado = new DActualizarPedidoBean();
		selectedPedido = new Pedido();
		selectedPedidos = new ArrayList<Pedido>();
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

	public String cargarPedidos() {
		this.idSucursal = 28;
		System.out.println("cargando Pedidos");
		pedidos = delegado.buscarPedidosSucursal(idSucursal);
		System.out.println("Cantidad pedidos sucursal 16: " + pedidos.size());
		System.out.println("Direccion primer pedido: " + pedidos.get(0).getDireccion());
		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
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
		//Logica para actualizar pedidos
		int nuevoValorPedido = -1;
		nuevoValorPedido = Integer.parseInt(radioButtonValue); 
		System.out.println("RADIO BUTTON VALUE!!!!! "+radioButtonValue);
		this.selectedPedido.setEstado(nuevoValorPedido);
		this.selectedPedido.setProductos(null);
		this.selectedPedido.setRepartidor(null);
		this.selectedPedido.setSucursal(null);
		this.selectedPedido.setUsuario(null);
		
		delegado.updatePedido(this.selectedPedido);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado del pedido actualizado correctamente"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-pedidos");
	}

}

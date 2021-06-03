import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;

import model.Pedido;
import model.Producto;
import model.Sucursal;

public class CarritoBean {

	private Producto producto;
	private DPedidoBean dcarrito;
	private Pedido pedido;
	private DelegadoBean delegado;
	private SucursalBean sucursalBean;
	
	private String tipoPago;
	private String tipoEntrega;
	
	private String idSucursal;
	private Sucursal sucursal;
	
	public CarritoBean() {
		super();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public DPedidoBean getDcarrito() {
		return dcarrito;
	}

	public void setDcarrito(DPedidoBean dcarrito) {
		this.dcarrito = dcarrito;
	}
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	public DelegadoBean getDelegado() {
		return delegado;
	}

	public void setDelegado(DelegadoBean delegado) {
		this.delegado = delegado;
	}
	
	public String getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public SucursalBean getSucursalBean() {
		return sucursalBean;
	}

	public void setSucursalBean(SucursalBean sucursalBean) {
		this.sucursalBean = sucursalBean;
	}

	public String enviarPedido() {
		for(Sucursal s: sucursalBean.getSucursales()) {
			if(s.getIdSucursal() == Integer.parseInt(idSucursal)) {
				sucursal = s;
			}
		}
		pedido.setSucursal(sucursal);
		sucursal.setPedidos(null);
		sucursal.setRepartidors(null);
		sucursal.setUsuario(null);
		return "Pagar";
	}
	

	public String confirmarPedido() {
		if(delegado.getUserFound()==null) {
			return "Autenticarse";
		}else {
			pedido.setUsuario(delegado.getUserFound());
			pedido.setTipoPago(Integer.parseInt(tipoPago));
			pedido.setTipoEntrega(Integer.parseInt(tipoEntrega));
			System.out.println("AYUDA" + tipoPago);
			return null;
		}
	}
	
	public void vaciarCarrito() {
		pedido = new Pedido();
		pedido.setProductos(new ArrayList<Producto>());
		pedido.setPrecioTotal(new BigDecimal(0));
	}
	
	public void eliminarProducto() throws IOException {
		pedido.getProductos().remove(producto);
		pedido.setPrecioTotal(pedido.getPrecioTotal().subtract(producto.getPrecio()));
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
	
	public void agregarProducto() throws IOException {
		if(pedido == null) {
			pedido = new Pedido();
			pedido.setProductos(new ArrayList<Producto>());
			pedido.setPrecioTotal(new BigDecimal(0));
		}
		producto.setPedidos(new ArrayList<Pedido>());
		producto.getPedidos().add(pedido);
		pedido.getProductos().add(producto);
		pedido.setPrecioTotal(pedido.getPrecioTotal().add(producto.getPrecio()));
	}
}

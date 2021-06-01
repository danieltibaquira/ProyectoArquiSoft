import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Pedido;
import model.Producto;

public class CarritoBean implements Serializable {

	private Producto producto;
	private DPedidoBean dcarrito;
	private Pedido pedido;
	
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

	public void confirmarPedido() {
		
	}
	
	public void vaciarCarrito() {
		pedido = new Pedido();
	}
	
	public void eliminarProducto() throws IOException {
		System.out.println("Elimando producto");
		pedido.getProductos().remove(producto);
		pedido.setPrecioTotal(pedido.getPrecioTotal().subtract(producto.getPrecio()));
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
	
	public void agregarProducto() throws IOException {
		System.out.println("agreagando producto");
		if(pedido == null) {
			pedido = new Pedido();
			pedido.setProductos(new ArrayList<Producto>());
			pedido.setPrecioTotal(new BigDecimal(0));

		}
		producto.setPedidos(new ArrayList<Pedido>());
		producto.getPedidos().add(pedido);
		pedido.getProductos().add(producto);
		//pedido.setUsuario(null);
		System.out.println(producto.getIdProducto());
		pedido.setPrecioTotal(pedido.getPrecioTotal().add(producto.getPrecio()));
	}
}

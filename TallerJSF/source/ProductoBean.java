import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Producto;

public class ProductoBean {
	
	private Producto product;

	public Producto getProduct() {
		return product;
	}

	public void setProduct(Producto product) {
		this.product = product;
	}

	public ProductoBean() {
		super();
		product = new Producto();
	}
	
	public String buscarProducto() {
		DelegadoBean delegadoBean = new DelegadoBean();
		product = delegadoBean.searchProduct(product);
		if(product != null) {
			return "detalleProducto?faces-redirect=true";
		}else {
			return "";
		}
	}
	
	public String registrarProducto() {
		DelegadoBean delegadoBean = new DelegadoBean();
		product = delegadoBean.createProduct(product);
		if(product != null) {
			return "detalleProducto?faces-redirect=true";
		}else {
			return "";
		}
	}

}

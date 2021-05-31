import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import model.Producto;

public class ProductBean {

	private List<Producto> productos;
	private DelegadoBean delegadoBean;
	private Producto arrayProdutos[];
	private Producto producto;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductBean() {
		productos = new ArrayList<Producto>();
		delegadoBean = new DelegadoBean();
		arrayProdutos = new Producto[0];
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public DelegadoBean getDelegadoBean() {
		return delegadoBean;
	}

	public void setDelegadoBean(DelegadoBean delegadoBean) {
		this.delegadoBean = delegadoBean;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto[] getArrayProdutos() {
		return arrayProdutos;
	}

	public void setArrayProdutos(Producto[] arrayProdutos) {
		this.arrayProdutos = arrayProdutos;
	}

	public String detalleUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		id = params.get("productId");
		System.out.println(id);
		return "result";
	}

	public String cargarProductos() {
		System.out.println("cargando productos");
		productos = delegadoBean.buscarProductos();
		System.out.println(productos.size());
		System.out.println(productos.get(0).getNombre());
		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
	}

}

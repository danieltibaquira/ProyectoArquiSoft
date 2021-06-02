import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.Pedido;
import model.Producto;
import net.bytebuddy.asm.Advice.This;

public class ProductBean implements Serializable {

	private List<Producto> productos;
	private Producto selectedProduct;
	private List<Producto> selectedProducts;
	private DProductoBean delegadoBean;
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
		delegadoBean = new DProductoBean();
		arrayProdutos = new Producto[0];
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public DProductoBean getDelegadoBean() {
		return delegadoBean;
	}

	public void setDelegadoBean(DProductoBean delegadoBean) {
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
	
	public Producto getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Producto selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<Producto> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<Producto> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public String detalleProducto() {
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
		System.out.println(productos.get(0).getNombreProducto());
		PrimeFaces.current().executeScript("getLocation()");
		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
	}
	
	public void openNew() {
		this.selectedProduct = new Producto();

	}
	
	public void saveProduct() {
		System.out.println(this.selectedProduct.getIdProducto());

		if(this.selectedProduct.getIdProducto() == 0) {
			System.out.println("Agregando producto");
			this.productos.add(this.selectedProduct);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sandwich añadido correctamente"));
			this.selectedProduct.setFotoProducto("https://source.unsplash.com/random/?sandwich");
			delegadoBean.createProduct(this.selectedProduct);
			
		}else {
			System.out.println("Actualizando producto");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
			this.selectedProduct.setPedidos(null);
			this.selectedProduct.setPromocion(null);
			delegadoBean.updateProduct(this.selectedProduct);
		}
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public void deleteProduct() {
		delegadoBean.deleteProduct(this.selectedProduct.getIdProducto());
		this.productos.remove(this.selectedProduct);
		this.selectedProduct = null;
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
	     PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public String getDeleteButtonMessage() {
		if(hasSelectedProducts()) {
			int size = this.selectedProducts.size();
			return size > 1? size + "Sandwiches seleccionados" : "Un sandwich seleccionado";
		}
		return "Eliminar";
	}
	
	public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }
	
	public void deleteSelectedProducts() {
		for(Producto p: selectedProducts) {
			delegadoBean.deleteProduct(p.getIdProducto());
		}
        this.productos.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
	
}

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.Producto;
import net.bytebuddy.asm.Advice.This;

public class ProductBean {

	private List<Producto> productos;
	private Producto selectedProduct;
	private List<Producto> selectedProducts;
	private DProductoBean delegadoBean;
	private Producto arrayProdutos[];
	
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

	public String cargarProductos() {
		System.out.println("cargando productos");
		productos = delegadoBean.buscarProductos();
		System.out.println(productos.size());
		System.out.println(productos.get(0).getNombreProducto());
		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
	}
	
	public void openNew() {
		this.selectedProduct = new Producto();
	}
	
	public void saveProduct() {
		if(this.selectedProduct.getIdProducto() == 0) {
			this.productos.add(this.selectedProduct);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sandwich añadido correctamente"));
			delegadoBean.createProduct(this.selectedProduct);
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
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

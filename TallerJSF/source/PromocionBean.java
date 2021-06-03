import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.Producto;
import model.Promocion;
import model.Repartidor;
import model.Sucursal;

public class PromocionBean {
	
	private List<Promocion> promociones;
	private Promocion selectedPromocion;
	private List<Promocion> selectedPromociones;
	private DPromocionBean delegadoBean;
	private ProductBean productBean;
	

	public PromocionBean() {
		super();
		delegadoBean = new DPromocionBean();
		promociones = new ArrayList<>();
	}
	public List<Promocion> getPromociones() {
		return promociones;
	}
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}
	public Promocion getSelectedPromocion() {
		return selectedPromocion;
	}
	public void setSelectedPromocion(Promocion selectedPromocion) {
		this.selectedPromocion = selectedPromocion;
	}
	public List<Promocion> getSelectedPromociones() {
		return selectedPromociones;
	}
	public void setSelectedPromociones(List<Promocion> selectedPromociones) {
		this.selectedPromociones = selectedPromociones;
	}
	public DPromocionBean getDelegadoBean() {
		return delegadoBean;
	}
	public void setDelegadoBean(DPromocionBean delegadoBean) {
		this.delegadoBean = delegadoBean;
	}
	
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	public void cargarPromociones() {
		System.out.println("cargando promociones");
		promociones = delegadoBean.buscarPromociones();
		System.out.println(promociones.get(0).getCiudad());
		
	}
	
	
	public void openNew() {
		this.selectedPromocion = new Promocion();
	}
	
	public void savePromocion() {
		System.out.println(this.selectedPromocion.getIdPromocion());

		if(this.selectedPromocion.getIdPromocion() == 0) {
			this.promociones.add(this.selectedPromocion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promocion añadida correctamente"));
		
			//this.selectedPromocion.setFotoPromocion("https://source.unsplash.com/random/?restaurant");
			this.selectedPromocion.setProductos(productBean.getSelectedProducts());

			System.out.println(productBean.getSelectedProducts().size());
			
			for(Producto p: productBean.getSelectedProducts()) {

				p.setPromocion(selectedPromocion);
				p.setPedidos(null);
				
			}

			delegadoBean.createPromocion(this.selectedPromocion);
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promocion actualizada"));
			//this.selectedRepartidor.setPedidos(null);
			this.selectedPromocion.setProductos(null);
			delegadoBean.updatePromocion(this.selectedPromocion);
		}
		PrimeFaces.current().executeScript("PF('managePromocionDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-promociones");
	}
	
	public void deletePromocion() {
		delegadoBean.deletePromocion(this.selectedPromocion.getIdPromocion());
		this.promociones.remove(this.selectedPromocion);
		this.selectedPromocion= null;
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promocion Eliminada"));
	     PrimeFaces.current().ajax().update("form:messages", "form:dt-promociones");
	}
	
	public String getDeleteButtonMessage() {
		if(hasSelectedPromociones()) {
			int size = this.selectedPromociones.size();
			return size > 1? size + " Promociones seleccionadas" : "1 Promocion seleccionada";
		}
		return "Eliminar";
	}
	
	public boolean hasSelectedPromociones() {

        return this.selectedPromociones != null && !this.selectedPromociones.isEmpty();
    }
	
	public void deleteSelectedPromociones() {
		for(Promocion p: selectedPromociones) {
			delegadoBean.deletePromocion(p.getIdPromocion());
		}
        this.promociones.removeAll(this.selectedPromociones);
        this.selectedPromociones= null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promociones eliminados"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-promociones");
        PrimeFaces.current().executeScript("PF('dtPromociones').clearFilters()");
    }

}

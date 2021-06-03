import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.util.UUID;
import org.primefaces.PrimeFaces;

import logicaInterfaz.LogicaSucursalesRemote;
import model.Producto;
import model.Repartidor;
import model.Sucursal;
import net.bytebuddy.asm.Advice.This;

public class RepartidorBean {
	
	private List<Repartidor> repartidores;
	private Repartidor selectedRepartidor;
	private List<Repartidor> selectedRepartidores;
	private DRepartidorBean delegadoBean;
	private String repartidorLocation;
	
	
	
	public RepartidorBean() {
		delegadoBean = new DRepartidorBean();
		repartidores = new ArrayList<Repartidor>();
		selectedRepartidores = new ArrayList<Repartidor>();
		PrimeFaces.current().executeScript("getLocation()");

	}
	public List<Repartidor> getRepartidores() {
		return repartidores;
	}
	public void setRepartidores(List<Repartidor> repartidores) {
		this.repartidores = repartidores;
	}
	public Repartidor getSelectedRepartidor() {
		return selectedRepartidor;
	}
	public void setSelectedRepartidor(Repartidor selectedRepartidor) {
		this.selectedRepartidor = selectedRepartidor;
	}
	public List<Repartidor> getSelectedRepartidores() {
		return selectedRepartidores;
	}
	public void setSelectedRepartidores(List<Repartidor> selectedRepartidores) {
		this.selectedRepartidores = selectedRepartidores;
	}
	public DRepartidorBean getDelegadoBean() {
		return delegadoBean;
	}
	public void setDelegadoBean(DRepartidorBean delegadoBean) {
		this.delegadoBean = delegadoBean;
	}
	
	public void openNew() {
		this.selectedRepartidor = new Repartidor();
	}
	
	public void cargarRepartidores() {
		System.out.println(delegadoBean.buscarRepartidores());
		repartidores = delegadoBean.buscarRepartidores();
	}
	
	public String getRepartidorLocation() {
		return repartidorLocation;
	}
	public void setRepartidorLocation(String repartidorLocation) {
		this.repartidorLocation = repartidorLocation;
	}
	public void saveRepartidor() {
		System.out.println(this.selectedRepartidor.getIdRepartidor());

		if(this.selectedRepartidor.getIdRepartidor() == 0) {
			this.repartidores.add(this.selectedRepartidor);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Repartidor añadido correctamente"));
			
			System.out.println("Repartidor nombre: " + this.selectedRepartidor.getNombreRepartidor());
			System.out.println("Repartidor nombre: " + this.selectedRepartidor.getNumero());
			this.selectedRepartidor.setFotoRepartidor("https://source.unsplash.com/random/?postman");
			this.selectedRepartidor.setPedidos(null);
			Sucursal sea = new Sucursal();
			sea.setIdSucursal(28);
			this.selectedRepartidor.setSucursal(sea);
			delegadoBean.createRepartidor(this.selectedRepartidor);
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Repartidor actualizado"));
			//this.selectedRepartidor.setPedidos(null);
			this.selectedRepartidor.setSucursal(null);
			delegadoBean.updateRepartidor(this.selectedRepartidor);
		}
		PrimeFaces.current().executeScript("PF('manageRepartidorDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-repartidores");
	}

	public void deleteRepartidor() {
		delegadoBean.deleteRepartidor(this.selectedRepartidor.getIdRepartidor());
		this.repartidores.remove(this.selectedRepartidor);
		this.selectedRepartidor= null;
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
	     PrimeFaces.current().ajax().update("form:messages", "form:dt-repartidores");
	}
	
	public String getDeleteButtonMessage() {
		if(hasSelectedRepartidores()) {
			int size = this.selectedRepartidores.size();
			return size > 1? size + "Repartidores seleccionados" : "Un repartidor seleccionado";
		}
		return "Eliminar";
	}
	
	public String getLocationMessage() {
		return "https://www.mapquest.com/embed/near-4.7316991999999995,-74.0655104?center=4.7316991999999995,-74.0655104&zoom=15&maptype=map";
	}
	
	public boolean hasSelectedRepartidores() {

        return this.selectedRepartidores != null && !this.selectedRepartidores.isEmpty();
    }
	
	public String locat() {
		String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1");
        String param2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param2");

		this.repartidorLocation = "https://www.mapquest.com/embed/near-"+param1+","+param2+"?center="+param1+","+param2+"&zoom=15&maptype=map";
		System.out.println("Link en locat: " + repartidorLocation);
		return this.repartidorLocation;

	}
	
	public void deleteSelectedRepartidores() {
		for(Repartidor r: selectedRepartidores) {
			delegadoBean.deleteRepartidor(r.getIdRepartidor());
		}
        this.repartidores.removeAll(this.selectedRepartidores);
        this.selectedRepartidores = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Repartidores eliminados"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-repartidores");
        PrimeFaces.current().executeScript("PF('dtRepartidores').clearFilters()");
    }

}

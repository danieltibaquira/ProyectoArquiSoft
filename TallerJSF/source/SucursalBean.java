import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.NewsAddress;

import org.primefaces.PrimeFaces;

import model.Sucursal;
import model.Usuario;

public class SucursalBean {
	private Sucursal sucursal;
	private DSucursalBean delegado;
	private List<Sucursal> sucursales;
	private List<Sucursal> selectedsucursales;
	private Sucursal arraySucursales[];
	private Sucursal selectedSucursal;
		
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public DSucursalBean getDelegado() {
		return delegado;
	}

	public void setDelegado(DSucursalBean delegado) {
		this.delegado = delegado;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<Sucursal> getSelectedsucursales() {
		return selectedsucursales;
	}

	public void setSelectedsucursales(List<Sucursal> selectedsucursales) {
		this.selectedsucursales = selectedsucursales;
	}

	public Sucursal[] getArraySucursales() {
		return arraySucursales;
	}

	public void setArraySucursales(Sucursal[] arraySucursales) {
		this.arraySucursales = arraySucursales;
	}

	public Sucursal getSelectedSucursal() {
		return selectedSucursal;
	}

	public void setSelectedSucursal(Sucursal selectedSucursal) {
		this.selectedSucursal = selectedSucursal;
	}

	public SucursalBean() {
		// TODO Auto-generated constructor stub
		super();
		this.sucursal = new Sucursal();
		sucursales = new ArrayList<Sucursal>();
		delegado = new DSucursalBean();
		arraySucursales = new Sucursal[0];
	}
	
	public String cargarSucursales() {
		System.out.println("cargando sucursales");
		sucursales = delegado.buscarSucursales();
		System.out.println(sucursales.size());
		System.out.println(sucursales.get(0).getNombreSucursal());
		return "<h:button  outcome=\"signup\" class=\"authButton\" type=\"button\" value=\"Registrarse\" icon=\"pi pi-check\" ></h:button>";
	}
	
	public void openNew() {
		this.selectedSucursal = new Sucursal();
	}
	
	public void saveSucursal() {
		if(this.selectedSucursal.getIdSucursal() == 0) {
			this.sucursales.add(this.selectedSucursal);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucursal agregada correctamente"));
			delegado.createSucursal(this.selectedSucursal);
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucursal Updated"));
			delegado.updateSucursal(this.selectedSucursal);
		}
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public void deleteSucursal() {
		delegado.deleteSucursal(this.selectedSucursal.getIdSucursal());
		this.sucursales.remove(this.selectedSucursal);
		this.selectedSucursal = null;
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucursal Removed"));
	     PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public String getDeleteButtonMessage() {
		if(hasSelectedSucursales()) {
			int size = this.selectedsucursales.size();
			return size > 1? size + " Sucursales seleccionadas" : "Una sucursal seleccionada";
		}
		return "Eliminar";
	}
	
	public boolean hasSelectedSucursales() {
        return this.selectedsucursales != null && !this.selectedsucursales.isEmpty();
    }
	
	public void deleteSelectedSucursales() {
		System.out.println("***************************************");
		System.out.println("Eliminando Mas de una sucursal!!!!!!");
		System.out.println("***************************************");
		for(Sucursal s: selectedsucursales) {
			delegado.deleteSucursal(s.getIdSucursal());
		}
        this.sucursales.removeAll(this.selectedsucursales);
        this.selectedsucursales = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sucursales");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
}

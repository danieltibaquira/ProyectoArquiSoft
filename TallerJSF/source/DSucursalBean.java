import java.util.List;

import logicaInterfaz.LogicaSucursalesRemote;
import model.Sucursal;

public class DSucursalBean {
	ServiceLocator sl = new ServiceLocator();
	LogicaSucursalesRemote servicioSucursales = sl.getServicioSucursal("sucursal");
	
	public DSucursalBean() {
		super();
	}
	
	public Sucursal searchSucursal(Sucursal sucursal) {
		Sucursal res = servicioSucursales.searchSucursal(sucursal);
		if(res != null) {
			return res; 
		}	
		return null;
	}
	
	public boolean deleteSucursal(int id) {
		return servicioSucursales.deleteSucursal(id);
	}
	
	public Sucursal createSucursal(Sucursal sucursal) {
		servicioSucursales.addSucursal(sucursal);
		return sucursal;
	}
	
	public Sucursal updateSucursal(Sucursal sucursal) {
		return servicioSucursales.updateSucursal(sucursal);
	}
	
	public List<Sucursal> buscarSucursales() {
		System.out.println("buscar sucursales");
		System.out.println(servicioSucursales.getAllSucursales().size());
		return servicioSucursales.getAllSucursales();
	}
}

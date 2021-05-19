package logicaImpl;

import java.util.List;

import javax.ejb.Stateless;

import datosInterface.ServiciosProductoRemote;
import datosInterface.ServiciosSucursalRemote;
import logicaInterfaz.LogicaSucursalesRemote;
import model.Producto;
import model.Sucursal;

/**
 * Session Bean implementation class LogicaSucursales
 */
@Stateless
public class LogicaSucursales implements LogicaSucursalesRemote {

    /**
     * Default constructor. 
     */
    public LogicaSucursales() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Sucursal addSucursal(Sucursal sucursal) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosSucursalRemote servicio = sl.getServicioSucursal("sucursal");
		Sucursal sucursalDB = servicio.addSucursal(sucursal);
		return sucursalDB;
	}

	@Override
	public Sucursal searchSucursal(Sucursal sucursal) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosSucursalRemote servicio = sl.getServicioSucursal("sucursal");
		Sucursal sucursalDB = servicio.searchSucursal(sucursal);
		return sucursalDB;
	}

	@Override
	public List<Sucursal> getAllSucursales() {
		ServiceLocator sl = new ServiceLocator();
		ServiciosSucursalRemote servicio = sl.getServicioSucursal("sucursal");
		List<Sucursal> sucursales = servicio.getAllSucursales();
		return sucursales;
	}

	@Override
	public boolean deleteSucursal(Integer id) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosSucursalRemote servicio = sl.getServicioSucursal("sucursal");
		boolean res = servicio.deleteSucursal(id);
		return false;
	}

}

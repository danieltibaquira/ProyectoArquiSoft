package logicaImpl;

import java.util.List;

import javax.ejb.Stateless;

import datosInterface.ServiciosRepartidorRemote;
import datosInterface.ServiciosSucursalRemote;
import logicaInterfaz.LogicaRepartidorRemote;
import model.Repartidor;
import model.Sucursal;

/**
 * Session Bean implementation class LogicaRepartidor
 */
@Stateless
public class LogicaRepartidor implements LogicaRepartidorRemote {

    /**
     * Default constructor. 
     */
    public LogicaRepartidor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Repartidor addRepartidor(Repartidor repartidor) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosRepartidorRemote servicio = sl.getServicioRepartidor("repartidor");
		Repartidor repartidorDB = servicio.addRepartidor(repartidor);
		return repartidorDB;
	}
	

	@Override
	public Repartidor searchRepartidor(Repartidor repartidor) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosRepartidorRemote servicio = sl.getServicioRepartidor("repartidor");
		Repartidor repartidorDB = servicio.searchRepartidor(repartidor);
		return repartidorDB;
	}

	@Override
	public List<Repartidor> getAllRepartidores() {
		ServiceLocator sl = new ServiceLocator();
		ServiciosRepartidorRemote servicio = sl.getServicioRepartidor("repartidor");
		List<Repartidor> repartidorDB = servicio.getAllRepartidores();
		return repartidorDB;
	}

	@Override
	public boolean deleteRepartidor(Integer id) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosRepartidorRemote servicio = sl.getServicioRepartidor("repartidor");
		boolean res = servicio.deleteRepartidor(id);
		return false;
	}
	
	@Override
	public Repartidor editRepartidor(Repartidor repartidor) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosRepartidorRemote servicio = sl.getServicioRepartidor("repartidor");
		Repartidor repartidorDB = servicio.editRepartidor(repartidor);
		return repartidorDB;
	}

}

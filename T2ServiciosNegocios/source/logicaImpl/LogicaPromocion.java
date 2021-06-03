package logicaImpl;

import java.util.List;

import javax.ejb.Stateless;

import datosInterface.ServiciosPromocionRemote;
import logicaInterfaz.LogicaPromocionRemote;
import model.Promocion;
import model.Sucursal;

/**
 * Session Bean implementation class LogicaPromocion
 */
@Stateless
public class LogicaPromocion implements LogicaPromocionRemote {

    /**
     * Default constructor. 
     */
    public LogicaPromocion() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Promocion addPromocion(Promocion promocion) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosPromocionRemote servicio = sl.getServicioPromocion("promocion");
		Promocion promocionDB = servicio.addPromocion(promocion);
		return promocionDB;
	}

	@Override
	public Promocion editPromocion(Promocion promocion) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosPromocionRemote servicio = sl.getServicioPromocion("promocion");
		Promocion promocionDB = servicio.editPromocion(promocion);
		return promocionDB;
	}
	
	@Override
	public Promocion searchPromocion(Promocion promocion) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosPromocionRemote servicio = sl.getServicioPromocion("promocion");
		Promocion promocionDB = servicio.searchPromocion(promocion);
		return promocionDB;
	}

	@Override
	public List<Promocion> getAllPromociones() {
		ServiceLocator sl = new ServiceLocator();
		ServiciosPromocionRemote servicio = sl.getServicioPromocion("promocion");
		List<Promocion> promociones = servicio.getAllPromociones();
		return promociones;
	}

	@Override
	public boolean deletePromocion(Integer id) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosPromocionRemote servicio = sl.getServicioPromocion("promocion");
		boolean res = servicio.deletePromocion(id);
		return false;
	}

}

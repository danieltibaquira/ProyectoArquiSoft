import java.util.List;

import logicaInterfaz.LogicaPromocionRemote;
import model.Promocion;
import model.Repartidor;

public class DPromocionBean {
	ServiceLocator sl = new ServiceLocator();
	LogicaPromocionRemote servicioPromocion = sl.getLogicaPromocion("promocion");
	
	public DPromocionBean() {
		super();
		sl = new ServiceLocator();
		servicioPromocion = sl.getLogicaPromocion("promocion");
		
	}
	
	public List<Promocion> buscarPromociones(){
		return servicioPromocion.getAllPromociones();
	}
	
	public boolean deletePromocion(int id) {
		return servicioPromocion.deletePromocion(id);
	}
	
	public Promocion createPromocion(Promocion promocion) {
		servicioPromocion.addPromocion(promocion);
		return promocion;
	}
	
	public Promocion updatePromocion(Promocion promocion) {
		return servicioPromocion.editPromocion(promocion);
		//return (product);
	}
}

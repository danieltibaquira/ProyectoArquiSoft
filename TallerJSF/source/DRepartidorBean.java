import java.util.List;

import logicaInterfaz.LogicaRepartidorRemote;
import model.Producto;
import model.Repartidor;

public class DRepartidorBean {
	
	ServiceLocator sl = new ServiceLocator();
	LogicaRepartidorRemote servicioRepartidor = sl.getLogicaRepartidor("repartidor");
	
	public DRepartidorBean() {
		super();
	}
	
	public List<Repartidor> buscarRepartidores(){
		return servicioRepartidor.getAllRepartidores();
	}
	
	public boolean deleteRepartidor(int id) {
		return servicioRepartidor.deleteRepartidor(id);
	}
	
	public Repartidor createRepartidor(Repartidor repartidor) {
		servicioRepartidor.addRepartidor(repartidor);
		return repartidor;
	}
	
	public Repartidor updateRepartidor(Repartidor repartidor) {
		return servicioRepartidor.editRepartidor(repartidor);
		//return (product);
	}
	
}

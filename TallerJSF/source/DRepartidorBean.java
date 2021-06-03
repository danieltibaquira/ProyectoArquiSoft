import java.util.List;

import logicaInterfaz.LogicaRepartidorRemote;
import model.Producto;
import model.Repartidor;

public class DRepartidorBean {
	
	ServiceLocator sl = new ServiceLocator();
	LogicaRepartidorRemote servicioRepartidor = sl.getLogicaRepartidor("repartidor");
	Repartidor repFound;
	
	public DRepartidorBean() {
		super();
		
	}
	
	public Repartidor getRepFound() {
		return repFound;
	}

	public void setRepFound(Repartidor repFound) {
		this.repFound = repFound;
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
	
	
	public Repartidor validateDel(Repartidor repartidor) {
		repFound = servicioRepartidor.searchRepartidor(repartidor);
		if(repFound != null) {
			return repFound;
		}else {
			System.out.print("Revise usuario y contraseña");
			return null;
		}
	}
}

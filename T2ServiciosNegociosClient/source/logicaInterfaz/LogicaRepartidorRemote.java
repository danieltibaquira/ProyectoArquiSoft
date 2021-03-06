package logicaInterfaz;

import java.util.List;

import javax.ejb.Remote;

import model.Repartidor;

@Remote
public interface LogicaRepartidorRemote {
	public Repartidor addRepartidor(Repartidor repartidor);
	public Repartidor searchRepartidor(Repartidor repartidor);
	public Repartidor editRepartidor(Repartidor repartidor);
	public List<Repartidor> getAllRepartidores();
	public boolean deleteRepartidor(Integer id);
}

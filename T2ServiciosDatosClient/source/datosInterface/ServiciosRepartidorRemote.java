package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Repartidor;

@Remote
public interface ServiciosRepartidorRemote {
	public Repartidor addRepartidor(Repartidor repartidor);
	public Repartidor searchRepartidor(Repartidor repartidor);
	public List<Repartidor> getAllRepartidores();
	public boolean deleteRepartidor(Integer id);
	public Repartidor editRepartidor(Repartidor repartidor);

}

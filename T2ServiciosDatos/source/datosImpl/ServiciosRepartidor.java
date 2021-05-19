package datosImpl;

import model.Repartidor;
import model.Sucursal;
import datosInterface.ServiciosRepartidorRemote;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ServiciosRepartidor
 */
@Stateless
public class ServiciosRepartidor implements ServiciosRepartidorRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    public ServiciosRepartidor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Repartidor addRepartidor(Repartidor repartidor) {
		try {
			Repartidor nRepartidor = entityManager.find(Repartidor.class, repartidor.getIdRepartidor());
			if (nRepartidor == null) {
				entityManager.persist(repartidor);
				return repartidor;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error creando sucursal");
			return null;
		}
	}

	@Override
	public Repartidor searchRepartidor(Repartidor repartidor) {
		String consulta = "SELECT u FROM Repartidor u WHERE u.nombre_repartidor=:nombre_repartidor";
		TypedQuery<Repartidor> query = entityManager.createQuery(consulta, Repartidor.class);
		query.setParameter("nombre", repartidor.getNombreRepartidor());
		query.setMaxResults(1);
		List<Repartidor> resultList = query.getResultList();

		if (resultList.size() == 0) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	@Override
	public List<Repartidor> getAllRepartidores() {
		List<Repartidor> repartidores = entityManager.createQuery("SELECT p FROM Repartidor p", Repartidor.class).getResultList();
		return repartidores;
	}

	@Override
	public boolean deleteRepartidor(Integer id) {
		try {
			Repartidor nRepartidor= entityManager.find(Repartidor.class, id);
			if (nRepartidor == null) {
				return false;
			} else {
				entityManager.remove(nRepartidor);
				return true;
			}
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

}

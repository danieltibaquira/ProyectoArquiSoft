package datosImpl;

import model.Producto;
import model.Repartidor;
import model.Sucursal;
import datosInterface.ServiciosRepartidorRemote;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
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
				entityManager.flush();
				entityManager.persist(repartidor);
				return repartidor;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error creando repartidor");
			return null;
		}
	}
	
	@Override
	public Repartidor editRepartidor(Repartidor repartidor) {
		try {
			Repartidor nRepartidor= entityManager.find(Repartidor.class, repartidor.getIdRepartidor());
			if (nRepartidor == null) {
				System.out.println("No hay repartidor");
				return repartidor;
			} else {
				System.out.println("Preparando Query ");
				String consulta = "UPDATE Repartidor SET nombre_repartidor=:nombre_repartidor, numero=:numero, latitude=:latitude, longitude=:longitude WHERE id_repartidor=:id_repartidor";
				Query query = entityManager.createQuery(consulta);
				System.out.println("Query creado ");
				query.setParameter("id_repartidor", repartidor.getIdRepartidor());
				query.setParameter("nombre_repartidor", repartidor.getNombreRepartidor());
				query.setParameter("numero", repartidor.getNumero());
				query.setParameter("latitude", repartidor.getLatitude());
				query.setParameter("longitude", repartidor.getLongitude());
				System.out.println("Creados los parametros");
				entityManager.flush();
				query.executeUpdate();
				System.out.println("Ejecutado el query");
				return repartidor;
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

	@Override
	public Repartidor searchRepartidor(Repartidor repartidor) {
		String consulta = "SELECT u FROM Repartidor u WHERE u.nombre_repartidor=:nombre_repartidor";
		TypedQuery<Repartidor> query = entityManager.createQuery(consulta, Repartidor.class);
		query.setParameter("nombre_repartidor", repartidor.getNombreRepartidor());
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
		System.out.println("Buscando repartidores");
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
	
	@Override
	public Repartidor editRepartidor(Repartidor repartidor) {
		try {
			Repartidor nRepartidor= entityManager.find(Repartidor.class, repartidor.getIdRepartidor());
			if (nRepartidor == null) {
				System.out.println("No hay repartidor");
				return repartidor;
			} else {
				System.out.println("Preparando Query ");
				String consulta = "UPDATE Repartidor SET nombre_repartidor=:nombre_repartidor, numero=:numero, latitude=:latitude, longitude=:longitude, pedidos=:pedidos WHERE id_repartidor=:id_repartidor";
				Query query = entityManager.createQuery(consulta);
				System.out.println("Query creado ");
				query.setParameter("id_repartidor", repartidor.getIdRepartidor());
				query.setParameter("pedidos", repartidor.getPedidos());
				query.setParameter("nombre_repartidor", repartidor.getNombreRepartidor());
				query.setParameter("numero", repartidor.getNumero());
				query.setParameter("latitude", repartidor.getLatitude());
				query.setParameter("longitude", repartidor.getLongitude());
				System.out.println("Creados los parametros Repartidor");
				entityManager.flush();
				query.executeUpdate();
				System.out.println("Ejecutado el query");
				return repartidor;
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

}

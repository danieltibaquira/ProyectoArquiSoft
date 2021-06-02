package datosImpl;

import datosInterface.ServiciosSucursalRemote;
import model.Producto;
import model.Sucursal;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ServiciosSucursal
 */
@Stateless
public class ServiciosSucursal implements ServiciosSucursalRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    public ServiciosSucursal() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Sucursal addSucursal(Sucursal sucursal) {
		try {
			Sucursal nSucursal = entityManager.find(Sucursal.class, sucursal.getIdSucursal());
			if (nSucursal == null) {
				entityManager.persist(sucursal);
				return sucursal;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error creando sucursal");
			return null;
		}
	}

	@Override
	public Sucursal searchSucursal(Sucursal sucursal) {
		String consulta = "SELECT p FROM Sucursal p WHERE p.nombre_sucursal=:nombre_sucursal";
		TypedQuery<Sucursal> query = entityManager.createQuery(consulta, Sucursal.class);
		query.setParameter("nombre_sucursal", sucursal.getNombreSucursal());
		query.setMaxResults(1);
		List<Sucursal> resultList = query.getResultList();

		if (resultList.size() == 0) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	@Override
	public List<Sucursal> getAllSucursales() {
		List<Sucursal> sucursales = entityManager.createQuery("SELECT p FROM Sucursal p", Sucursal.class).getResultList();
		return sucursales;
	}

	@Override
	public boolean deleteSucursal(Integer id) {
		try {
			Sucursal nSucursal= entityManager.find(Sucursal.class, id);
			if (nSucursal == null) {
				return false;
			} else {
				entityManager.remove(nSucursal);
				return true;
			}
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

}

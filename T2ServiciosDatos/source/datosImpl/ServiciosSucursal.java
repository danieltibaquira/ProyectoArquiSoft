package datosImpl;

import datosInterface.ServiciosSucursalRemote;
import model.Producto;
import model.Sucursal;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
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
		String consulta = "SELECT u FROM Sucursal u WHERE u.nombre_sucursal=:nombre_sucursal";
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

	@Override
	public Sucursal updateSucursal(Sucursal sucursal) {
		System.out.println("OMG llego a la BD");
		try {
			Sucursal nSucursal = entityManager.find(Sucursal.class, sucursal.getIdSucursal());
			if (nSucursal == null) {
				System.out.println("No hay sucursal");
				return sucursal;
			} else {
				System.out.println("Preparando Query ");
				String consulta = "UPDATE Sucursal SET contrasena_sucursal=:contrasena_sucursal, direccion_sucursal=:direccion_sucursal, nombre_sucursal=:nombre_sucursal, telefono=:telefono WHERE id_sucursal=:id_sucursal";
				System.out.println("Preparando Query 2");
				//TypedQuery<Sucursal> query = entityManager.createQuery(consulta, Sucursal.class);
				Query query = entityManager.createQuery(consulta);
				System.out.println("Query creado ");
				query.setParameter("id_sucursal", sucursal.getIdSucursal());
				query.setParameter("contrasena_sucursal", sucursal.getContrasenaSucursal());
				query.setParameter("direccion_sucursal", sucursal.getDireccionSucursal());
				query.setParameter("nombre_sucursal", sucursal.getNombreSucursal());
				query.setParameter("telefono", sucursal.getTelefono());
				System.out.println("Creados los parametros");
				query.executeUpdate();
				System.out.println("Ejecutado el query");
				return sucursal;
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
			return null;
		}
	}

}

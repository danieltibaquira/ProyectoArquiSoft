package datosImpl;

import datosInterface.ServiciosPromocionRemote;
import model.Promocion;
import model.Sucursal;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ServiciosPromocion
 */
@Stateless
public class ServiciosPromocion implements ServiciosPromocionRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    public ServiciosPromocion() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Promocion addPromocion(Promocion promocion) {
		try {
			Promocion nPromocion = entityManager.find(Promocion.class, promocion.getIdPromocion());
			if (nPromocion == null) {
				entityManager.persist(promocion);
				return promocion;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error creando sucursal");
			return null;
		}
	}

	@Override
	public Promocion searchPromocion(Promocion promocion) {
		String consulta = "SELECT u FROM Promocion u WHERE u.id_promocion=:id_promocion";
		TypedQuery<Promocion> query = entityManager.createQuery(consulta, Promocion.class);
		query.setParameter("descripcion", promocion.getDescripcion());
		query.setMaxResults(1);
		List<Promocion> resultList = query.getResultList();

		if (resultList.size() == 0) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	@Override
	public List<Promocion> getAllPromociones() {
		List<Promocion> promociones = entityManager.createQuery("SELECT p FROM Promocion p", Promocion.class).getResultList();
		return promociones;
	}

	@Override
	public boolean deletePromocion(Integer id) {
		try {
			Promocion nPromocion= entityManager.find(Promocion.class, id);
			if (nPromocion == null) {
				return false;
			} else {
				entityManager.remove(nPromocion);
				return true;
			}
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

}

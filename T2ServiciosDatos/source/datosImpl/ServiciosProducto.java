package datosImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import datosInterface.ServiciosProductoRemote;
import model.Producto;

/**
 * Session Bean implementation class ServiciosProducto
 */
@Stateless
@LocalBean
public class ServiciosProducto implements ServiciosProductoRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    public ServiciosProducto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Producto addProducto(Producto product) {
		try {
			Producto nProduct = entityManager.find(Producto.class, product.getIdProducto());
			if (nProduct == null) {
				entityManager.persist(product);
				return product;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

	@Override
	public boolean deleteProducto(Integer id) {
		try {
			Producto nProducto = entityManager.find(Producto.class, id);
			if (nProducto == null) {
				return false;
			} else {
				entityManager.remove(nProducto);
				return true;
			}
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

	@Override
	public List<Producto> getAllProductos() {
		List<Producto> productos = entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
		return productos;
	}

	@Override
	public Producto searchProducto(Producto producto) {
		String consulta = "SELECT u FROM Producto u WHERE u.nombre=:nombre";
		TypedQuery<Producto> query = entityManager.createQuery(consulta, Producto.class);
		query.setParameter("nombre", producto.getNombre());
		query.setMaxResults(1);
		List<Producto> resultList = query.getResultList();

		if (resultList.size() == 0) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

}

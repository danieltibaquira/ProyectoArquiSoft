package datosImpl;

import datosInterface.ServiciosPromocionRemote;
import model.Producto;
import model.Promocion;
import model.Sucursal;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
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
				System.out.println("Numero de productos en datos antes del flush: " + promocion.getProductos().size());
				entityManager.flush();
				System.out.println("Numero de productos en datos: " + promocion.getProductos().size());
				for(Producto p: promocion.getProductos()) {
					p.setPromocion(promocion);
					entityManager.merge(p);	
					entityManager.flush();
				}
				
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
	public Promocion editPromocion(Promocion promocion) {
		try {
			Promocion nPromocion = entityManager.find(Promocion.class, promocion.getProductos());
			if (nPromocion == null) {
				System.out.println("No hay promocion");
				return promocion;
			} else {
				System.out.println("Preparando Query ");
				String consulta = "UPDATE Promocion SET "+ 
									"fecha_vencimiento=:fecha_vencimiento, "+ 
									"descuento=:descuento, "+
									"descripcion=:descripcion, "+
									"tipo_promocion=:tipo_promocion, "+
									"zona_validez=:zona_validez, "+
									"ciudad=:ciudad, "+
									"WHERE id_promocion=:id_promocion";
				
				Query query = entityManager.createQuery(consulta);
				
				System.out.println("Query creado ");
				
				query.setParameter("fecha_vencimiento", promocion.getIdPromocion());
				query.setParameter("descuento", promocion.getDescuento());
				query.setParameter("descripcion", promocion.getDescripcion());
				query.setParameter("tipo_promocion", promocion.getTipoPromocion());
				query.setParameter("zona_validez", promocion.getZonaValidez());
				query.setParameter("ciudad", promocion.getCiudad());
				
				System.out.println("Creados los parametros");
				
				entityManager.flush();
				
				query.executeUpdate();
				
				System.out.println("Ejecutado el query");
				return promocion;
				/* id_promocion int
				 * fecha_vencimiento date
				 * descuento double
				 * descripcion varchar(1000)
				 * tipo_promocion int
				 * zona validez varchar(30)
				 * ciudad varchar(25)
				 * */
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}


	@Override
	public Promocion searchPromocion(Promocion promocion) {
		String consulta = "SELECT u FROM Promocion u WHERE u.id_promocion=:id_promocion";
		TypedQuery<Promocion> query = entityManager.createQuery(consulta, Promocion.class);
		query.setParameter("id_promocion", promocion.getIdPromocion());
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

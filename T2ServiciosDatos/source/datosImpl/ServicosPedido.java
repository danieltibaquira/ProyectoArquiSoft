package datosImpl;

import model.Pedido;
import model.Sucursal;
import model.Usuario;
import datosInterface.ServicosPedidoRemote;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ServicosPedido
 */
@Stateless
public class ServicosPedido implements ServicosPedidoRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    public ServicosPedido() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Pedido addPedido(Pedido pedido) {
		try {
			Pedido nPedido = entityManager.find(Pedido.class, pedido.getIdPedido());
			if (nPedido == null) {
				entityManager.persist(pedido);
				return pedido;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error creando sucursal");
			return null;
		}
	}

	@Override
	public Pedido searchPedido(Pedido pedido) {
		String consulta = "SELECT u FROM Pedido u WHERE u.id_pedido=:id_pedido";
		TypedQuery<Pedido> query = entityManager.createQuery(consulta, Pedido.class);
		query.setParameter("id_pedido", pedido.getIdPedido());
		query.setMaxResults(1);
		List<Pedido> resultList = query.getResultList();

		if (resultList.size() == 0) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	@Override
	public List<Pedido> getAllPedidos() {
		List<Pedido> pedidos = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
		return pedidos;
	}

	@Override
	public List<Pedido> getPedidosUsuario(Usuario usuario) {
		System.out.println("ENTRE");
		String consulta = "SELECT u FROM Pedido u WHERE u.usuario=:usuario";
		TypedQuery<Pedido> query = entityManager.createQuery(consulta, Pedido.class);
		query.setParameter("usuario", usuario);
		List<Pedido> resultList = query.getResultList();
		if (resultList.size() == 0) {
			System.out.println("NO hay resultados");
			return null;
		} else {
			System.out.println("hay resultados");
			System.out.println(resultList.get(0).getIdPedido());
			return resultList;
		}
	}
	
	
	@Override
	public List<Pedido> getAllPedidosByIDRepartidor(int idRepartidor){
		List<Pedido> pedidos = entityManager.createQuery("SELECT p FROM Pedido p WHERE Repartidor_id_repartidor=:id_repartidor", Pedido.class).setParameter("id_sucursal", idRepartidor).getResultList();
		return pedidos;
	}

	public Pedido updatePedido(Pedido pedido) {
		try {
			Pedido nPedido = entityManager.find(Pedido.class, pedido.getIdPedido());
			if (nPedido == null) {
				System.out.println("No hay pedido");
				return pedido;
			} else {
				System.out.println("Preparando Query ");
				String consulta = "UPDATE Pedido SET direccion=:direccion, estado=:estado, fecha=:fecha, precioTotal=:precioTotal, tipoPago=:tipoPago, repartidor=:repartidor WHERE id_pedido=:id_pedido";
				System.out.println("Preparando Query 2");
				//TypedQuery<Sucursal> query = entityManager.createQuery(consulta, Sucursal.class);
				Query query = entityManager.createQuery(consulta);
				System.out.println("Query creado ");
				query.setParameter("id_pedido", pedido.getIdPedido());
				query.setParameter("repartidor", pedido.getRepartidor());
				query.setParameter("direccion", pedido.getDireccion());
				query.setParameter("estado", pedido.getEstado());
				query.setParameter("fecha", pedido.getFecha());
				query.setParameter("precioTotal", pedido.getPrecioTotal());
				query.setParameter("tipoPago", pedido.getTipoPago());
				System.out.println("Creados los parametros Pedido");
				entityManager.flush();
				query.executeUpdate();
				System.out.println("Ejecutado el query");
				return pedido;
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
			return null;
		}
	}

	@Override
	public List<Pedido> getAllPedidosByIDSucursal(int idSucursal) {
		List<Pedido> pedidos = entityManager.createQuery("SELECT p FROM Pedido p WHERE Sucursal_id_sucursal=:id_sucursal", Pedido.class).setParameter("id_sucursal", idSucursal).getResultList();
		return pedidos;
	}

}

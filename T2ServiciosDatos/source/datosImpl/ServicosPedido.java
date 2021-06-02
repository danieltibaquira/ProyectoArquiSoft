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

}

package datosImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import datosInterface.ServiciosUsuarioRemote;
import model.Pedido;
import model.Usuario;

/**
 * Session Bean implementation class ServiciosUsuario
 */
@Stateless
@LocalBean
public class ServiciosUsuario implements ServiciosUsuarioRemote {

	/**
	 * Default constructor.
	 */

	@PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public ServiciosUsuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario addUsuario(Usuario user) {
		try {
			Usuario nuser = entityManager.find(Usuario.class, user.getIdUsuario());
			if (nuser == null) {
				entityManager.persist(user);
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

	@Override
	public Usuario update(Usuario user) {
		try {
			Usuario nuser = entityManager.find(Usuario.class, user.getIdUsuario());
			if (nuser == null) {
				return null;
			} else {
				entityManager.merge(user);
				return user;
			}
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

	@Override
	public Usuario validar(String userName, String password) {
		String consulta = "SELECT u FROM Usuario u WHERE u.username=:userName AND u.contrasena=:password";
		TypedQuery<Usuario> query = entityManager.createQuery(consulta, Usuario.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		query.setMaxResults(1);
		List<Usuario> resultList = query.getResultList();

		
		if (resultList.size() == 0) {

			return null;
		} else {
			return resultList.get(0);
		}
	}


	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuarios = entityManager.createQuery("SELECT p FROM Usuario p", Usuario.class).getResultList();
		
		return usuarios;
	}

	@Override
	public boolean deleteUsuario(Integer id) {
		try {
			Usuario nuser = entityManager.find(Usuario.class, id);
			if (nuser == null) {
				return false;
			} else {
				entityManager.remove(nuser);
				return true;
			}
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

}

package logicaImpl;

import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import datosInterface.ServiciosUsuarioRemote;
import logicaInterfaz.LogicaUsuariosRemote;
import model.Usuario;

/**
 * Session Bean implementation class LogicaUsuarios
 */
@Stateless
@LocalBean
public class LogicaUsuarios implements LogicaUsuariosRemote {

    /**
     * Default constructor. 
     */
    public LogicaUsuarios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Usuario addUsuario(Usuario user) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosUsuarioRemote servicio = sl.getServicio("usuario");
		Usuario userBD = servicio.addUsuario(user);
		return userBD;
	}

	@Override
	public Usuario update(Usuario user) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosUsuarioRemote servicio = sl.getServicio("usuario");
		Usuario userBD = servicio.update(user);
		return userBD;
	}

	@Override
	public Usuario validar(String username, String password) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosUsuarioRemote servicio = sl.getServicio("usuario");
		Usuario user = servicio.validar(username, password);
		//if(user!=null) {
			//correoConfirmacion(user.getCorreo());
		//}
		return user;
	}


	@Override
	public List<Usuario> getAllUsuarios() {
		ServiceLocator sl = new ServiceLocator();
		ServiciosUsuarioRemote servicio = sl.getServicio("usuario");
		List<Usuario> usuarios = servicio.getAllUsuarios();
		return usuarios;
	}

	@Override
	public boolean deleteUsuario(Integer id) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosUsuarioRemote servicio = sl.getServicio("usuario");
		boolean resultado = servicio.deleteUsuario(id);
		return resultado;
	}


	/*private boolean correoConfirmacion(String enviar) {
		String usuario = "takina";
		String contrasena = "takina";
		String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
		String DESTINATION = "MyQueue";

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "http-remoting://localhost:8100");
		env.put(Context.SECURITY_PRINCIPAL, usuario);
		env.put(Context.SECURITY_CREDENTIALS, contrasena);
		env.put("jboss.naming.client.ejb.context", true);

		

		try {
			InitialContext ic = new InitialContext(env);

			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ic.lookup(CONNECTION_FACTORY);
			QueueConnection connection = connectionFactory.createQueueConnection(usuario, contrasena);

			javax.jms.Queue queue = (javax.jms.Queue) ic.lookup(DESTINATION);
			QueueSession session;
			QueueSender sender;

			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createSender(queue);
			Message mensaje = session.createTextMessage(enviar);
			sender.send(mensaje);

			sender.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace(System.out);
		}
		return false;
	}*/

}

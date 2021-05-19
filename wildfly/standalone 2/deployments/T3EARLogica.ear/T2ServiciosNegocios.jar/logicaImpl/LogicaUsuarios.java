package logicaImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import datosInterface.ServiciosUsuarioRemote;
import logicaInterfaz.LogicaUsuariosRemote;
import model.Usuario;
import envios.Correo;

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
		if(user != null) {
			//Correo.enviar("Ha ingresado al sistema de Daniel :)");
		}
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


}

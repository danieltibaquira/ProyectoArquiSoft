package logicaInterfaz;

import java.util.List;

import javax.ejb.Remote;

import model.Usuario;

@Remote
public interface LogicaUsuariosRemote {
	public Usuario addUsuario(Usuario user);
	public Usuario update(Usuario user);
	public Usuario validar(String username, String password);
	public List<Usuario> getAllUsuarios();
	public boolean deleteUsuario(Integer id);
}

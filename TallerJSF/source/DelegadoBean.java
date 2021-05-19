//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.util.List;

import logicaInterfaz.LogicaUsuariosRemote;
import logicaInterfaz.logicaProductosRemote;
import model.Producto;
import model.Usuario;

@ManagedBean(name = "delegadoBean1")
@SessionScoped
public class DelegadoBean {
	//@ManagedProperty(value="#{servicebean}")
	ServiceLocator sl = new ServiceLocator();
	LogicaUsuariosRemote servicioUsuario = sl.getServicio("usuario");
	logicaProductosRemote serviciosProducto = sl.getServicioProducto("producto");

	public DelegadoBean() {
		super();
	}
	public Usuario validateUser(Usuario user) {
		Usuario userFound = servicioUsuario.validar(user.getUsername(), user.getPassword());
		if(userFound != null) { 
			return userFound;
		}else {
			if(!userExist(user.getUsername())) {
				System.out.print("Password Incorrecto");
			}
			return null;
		}
	}
	
	public Usuario createUser(Usuario user) {
		/*if(!userExist(user.getUsername())) {
			System.out.print("Usuario ya existe");
			return null;
		}else {*/
		servicioUsuario.addUsuario(user);
		return user;
		//}
	}
	
	public boolean userExist(String userName) {
		boolean res = false;
		
		List<Usuario> allUsers = servicioUsuario.getAllUsuarios();
		for(Usuario u: allUsers) {
			if(u.getUsername() == userName) {
				res = true;
			}
		}
	
		return res;
	}
	
	public Producto searchProduct(Producto producto) {
		Producto res = serviciosProducto.searchProducto(producto);
		if(res != null) {
			return res; 
		}	
		return null;
	}
	
	public Producto createProduct(Producto product) {
		serviciosProducto.addProducto(product);
		return product;
	}
	
}

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;

import logicaInterfaz.LogicaUsuariosRemote;
import logicaInterfaz.logicaProductosRemote;
import model.*;

@ManagedBean(eager = true)
@SessionScoped
public class DelegadoBean {
	ServiceLocator sl = new ServiceLocator();
	LogicaUsuariosRemote servicioUsuario = sl.getServicio("usuario");
	logicaProductosRemote serviciosProducto = sl.getServicioProducto("producto");
	Usuario userFound;
	
	
	
	public Usuario getUserFound() {
		return userFound;
	}
	public void setUserFound(Usuario userFound) {
		this.userFound = userFound;
	}
	public DelegadoBean() {
		super();
		//userFound=new Usuario();
	}
	public Usuario validateUser(Usuario user) {
		if(userFound == null) {
			System.out.println("usuario vacio");
		}else {
			System.out.println("usuario lleno");
			System.out.println(userFound.getApellidoUsuario());
		}
		userFound = servicioUsuario.validar(user.getUsername(), user.getContrasena());
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
		userFound = user;
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
	
	public List<Producto> buscarProductos() {
		return serviciosProducto.getAllProductos();
	}
	
}

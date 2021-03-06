import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
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
			userFound.setPedidos(null);
			if(userFound.getRol()==1) {
				System.out.println("El usuario es admin");
				System.out.println("Sucursal " + userFound.getSucursals().get(0).getNombreSucursal());
			}else{
				userFound.setSucursals(null);
			}
			//userFound.setPedidos(new ArrayList<Pedido>());
			return userFound;
		}else {
			if(!userExist(user.getUsername())) {
				System.out.print("Password Incorrecto");
			}
			return null;
		}
	}
	
	public Usuario createUser(Usuario user) {
		userFound = user;
		servicioUsuario.addUsuario(user);
		return user;
		//}
	}
	
	public void cerrarSesion() throws IOException {
		userFound = null;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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
		System.out.println("buscar producto");
		System.out.println(serviciosProducto.getAllProductos().size());
		return serviciosProducto.getAllProductos();
	}
	
}

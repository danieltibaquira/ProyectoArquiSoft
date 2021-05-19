//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import model.Usuario;

@ManagedBean(name ="registroBean1")
@SessionScoped
public class RegistroBean {

	private Usuario user;
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public RegistroBean() {
		super();
		user = new Usuario();
	}
	


	public String validarUsuario() {
		DelegadoBean delegadoBean = new DelegadoBean();
		user = delegadoBean.validateUser(user);
		if(user != null) {
			return "bienvenido?faces-redirect=true";
		}else {
			return "";
		}
		
	}
	
	public String registrarUsuario(){
		DelegadoBean delegadoBean = new DelegadoBean();
		user = delegadoBean.createUser(user);
		System.out.print("Usuario creado: " + user.getCorreo());
		if(user != null) {
			return "bienvenido?faces-redirect=true";
		}else {
			return "";
		}
	}
	
}

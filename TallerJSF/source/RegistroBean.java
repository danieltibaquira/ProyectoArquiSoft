

import model.Usuario;

public class RegistroBean {

	private Usuario user;
	private DelegadoBean delegado;
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public RegistroBean() {
		super();
		user = new Usuario();
		//delegadoBean = new DelegadoBean();
	}

	public DelegadoBean getDelegado() {
		return delegado;
	}

	public void setDelegado(DelegadoBean delegado) {
		this.delegado = delegado;
	}

	public String validarUsuario() {
		System.out.println("Validando usuario");
		user = delegado.validateUser(user);
		if(user != null) {
			if(user.getRol()==1) return "Admin";
			else return "Exito";
		}else {
			return "";
		}
		
	}
	
	public String registrarUsuario(){
		user = delegado.createUser(user);
		System.out.print("Usuario creado: " + user.getCorreo());
		if(user != null) {
			return "Exito";
		}else {
			return "";
		}
	}
	
}

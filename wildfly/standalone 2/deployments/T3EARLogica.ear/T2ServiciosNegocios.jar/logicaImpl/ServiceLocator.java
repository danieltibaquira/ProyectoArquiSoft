package logicaImpl;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import datosInterface.ServiciosUsuarioRemote;


public class ServiceLocator {

	private Context crearContexto() {
		Properties jndlProperties = new Properties();
		jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndlProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndlProperties.put(Context.SECURITY_PRINCIPAL, "daniel");
		jndlProperties.put(Context.SECURITY_CREDENTIALS, "pablo2015");
		jndlProperties.put("jboss.naning.client.ejb.context", true);
		
		Context ctx = null;
		try {
			ctx = new InitialContext(jndlProperties);
		}catch(NamingException e) {
			e.printStackTrace();
		}
		return ctx;
	}
	
	public ServiciosUsuarioRemote getServicio(String servicio) {
		ServiciosUsuarioRemote servicios = null;
		if(servicio.equals("usuario")) {
			servicios = getServicioUsuarios();
		}
		return (ServiciosUsuarioRemote)servicios;
	}
	
	public ServiciosUsuarioRemote getServicioUsuarios() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = "T2EARServiciosDatos";
		String moduleName = "T2ServiciosDatos";
		String beanName = "ServiciosUsuario";
		String viewClassName = ServiciosUsuarioRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosUsuarioRemote serviciosUsuarios = null;
		try {
			serviciosUsuarios = (ServiciosUsuarioRemote) ctx.lookup("ejb:T2EARServiciosDatos/T2ServiciosDatos/ServiciosUsuario!datosInterface.ServiciosUsuarioRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosUsuarioRemote)serviciosUsuarios;
	}	

	
}

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import logicaInterfaz.LogicaPromocionRemote;
import logicaInterfaz.LogicaRepartidorRemote;
import logicaInterfaz.LogicaSucursalesRemote;
import logicaInterfaz.LogicaUsuariosRemote;
import logicaInterfaz.logicaProductosRemote;


@ManagedBean(name = "serviceBean")
@RequestScoped
public class ServiceLocator {
	
	
	
	public ServiceLocator() {
		super();
	}

	private Context crearContexto() {
		Properties jndlProperties = new Properties();
		jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, org.wildfly.naming.client.WildFlyInitialContextFactory.class.getName());
		//jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		//jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
		jndlProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");
		jndlProperties.put(Context.SECURITY_PRINCIPAL, "daniel");
		jndlProperties.put(Context.SECURITY_CREDENTIALS, "pablo2015");
		jndlProperties.put("jboss.naming.client.ejb.context", true);
		
		Context ctx = null;
		try {
			ctx = new InitialContext(jndlProperties);
		}catch(NamingException e) {
			
			System.out.print(e.getMessage());
		}
		return ctx;
	}
	
	public LogicaUsuariosRemote getServicio(String servicio) {
		LogicaUsuariosRemote servicios = null;
		if(servicio.equals("usuario")) {
			servicios = getServicioUsuarios();
		}
		return (LogicaUsuariosRemote)servicios;
	}
	
	public LogicaUsuariosRemote getServicioUsuarios() {
		Context ctx = crearContexto();
		String namespace = "ejb:";
		String appName = "T2EARLogica";
		String moduleName = "T2ServiciosNegocios";
		String beanName = "LogicaUsuarios";
		String viewClassName = LogicaUsuariosRemote.class.getName();
		
		LogicaUsuariosRemote serviciosUsuarios = null;
		try {
			//serviciosUsuarios = (LogicaUsuariosRemote) ctx.lookup(
					//namespace + appName + "/" + moduleName + "/" + beanName + "!" + viewClassName);
			serviciosUsuarios = (LogicaUsuariosRemote) ctx.lookup("ejb:T2EARLogica/T2ServiciosNegocios/LogicaUsuarios!logicaInterfaz.LogicaUsuariosRemote");
		} catch(NamingException e) {
			System.out.print(e.getMessage());
			//e.printStackTrace();
		}
		return (LogicaUsuariosRemote)serviciosUsuarios;
	}
	

	public logicaProductosRemote getServicioProducto(String servicio) {
		logicaProductosRemote servicios = null;
		if(servicio.equals("producto")){
			servicios = getServiciosProductoConnect();
		}
		return (logicaProductosRemote) servicios;
	}

	
	public logicaProductosRemote getServiciosProductoConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = "T2EARLogica";
		String moduleName = "T2ServiciosNegocios";
		String beanName = "logicaProductos";
		String viewClassName = logicaProductosRemote.class.getName();
		
		System.out.println(viewClassName);
		
		logicaProductosRemote serviciosProductos = null;
		try {
			serviciosProductos = (logicaProductosRemote) ctx.lookup("ejb:T2EARLogica/T2ServiciosNegocios/logicaProductos!logicaInterfaz.logicaProductosRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (logicaProductosRemote)serviciosProductos;
	}
	
	public LogicaSucursalesRemote getServicioSucursal(String servicio) {
		LogicaSucursalesRemote servicios = null;
		if(servicio.equals("sucursal")){
			servicios = getLogicaSucursalConnect();
		}
		return (LogicaSucursalesRemote) servicios;
	}

	
	public LogicaSucursalesRemote getLogicaSucursalConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = "T2EARLogica";
		String moduleName = "T2ServiciosNegocios";
		String beanName = "LogicaSucursales";
		String viewClassName = LogicaSucursalesRemote.class.getName();
		
		System.out.println(viewClassName);
		
		LogicaSucursalesRemote serviciosProductos = null;
		try {
			serviciosProductos = (LogicaSucursalesRemote) ctx.lookup("ejb:T2EARLogica/T2ServiciosNegocios/LogicaSucursales!logicaInterfaz.LogicaSucursalesRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (LogicaSucursalesRemote)serviciosProductos;
	}
	
	public LogicaRepartidorRemote getLogicaRepartidor(String servicio) {
		LogicaRepartidorRemote servicios = null;
		if(servicio.equals("sucursal")){
			servicios = getLogicaRepartidorConnect();
		}
		return (LogicaRepartidorRemote) servicios;
	}

	
	public LogicaRepartidorRemote getLogicaRepartidorConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = "T2EARLogica";
		String moduleName = "T2ServiciosNegocios";
		String beanName = "LogicaSucursales";
		String viewClassName = LogicaRepartidorRemote.class.getName();
		
		System.out.println(viewClassName);
		
		LogicaRepartidorRemote serviciosProductos = null;
		try {
			serviciosProductos = (LogicaRepartidorRemote) ctx.lookup("ejb:T2EARLogica/T2ServiciosNegocios/LogicaRepartidor!logicaInterfaz.LogicaRepartidorRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (LogicaRepartidorRemote)serviciosProductos;
	}
	
	public LogicaPromocionRemote getLogicaPromocion(String servicio) {
		LogicaPromocionRemote servicios = null;
		if(servicio.equals("promocion")){
			servicios = getLogicaPromocionConnect();
		}
		return (LogicaPromocionRemote) servicios;
	}

	
	public LogicaPromocionRemote getLogicaPromocionConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = "T2EARLogica";
		String moduleName = "T2ServiciosNegocios";
		String beanName = "LogicaSucursales";
		String viewClassName = LogicaRepartidorRemote.class.getName();
		
		System.out.println(viewClassName);
		
		LogicaPromocionRemote serviciosPromocion = null;
		try {
			serviciosPromocion = (LogicaPromocionRemote) ctx.lookup("ejb:T2EARLogica/T2ServiciosNegocios/LogicaPromocion!logicaInterfaz.LogicaPromocionRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (LogicaPromocionRemote)serviciosPromocion;
	}
	
}
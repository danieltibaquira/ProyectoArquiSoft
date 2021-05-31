package logicaImpl;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import datosInterface.ServiciosUsuarioRemote;
import datosInterface.ServicosPedidoRemote;
import datosInterface.ServiciosProductoRemote;
import datosInterface.ServiciosPromocionRemote;
import datosInterface.ServiciosRepartidorRemote;
import datosInterface.ServiciosSucursalRemote;

public class ServiceLocator {

	private String nombreEAR = "ProyectoEARServiciosDatos";
	
	private Context crearContexto() {
		//AHHHHHHHHHHHHHHHH
		/*Properties jndlProperties = new Properties();
		jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, org.wildfly.naming.client.WildFlyInitialContextFactory.class.getName());
		jndlProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8680");
		jndlProperties.put(Context.SECURITY_PRINCIPAL, "adminJohan");
		jndlProperties.put(Context.SECURITY_CREDENTIALS, "123456");
		jndlProperties.put("jboss.naning.client.ejb.context", true);*/
		Properties jndlProperties = new Properties();
		jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		//jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		//jndlProperties.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
		jndlProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8390");
		jndlProperties.put(Context.SECURITY_PRINCIPAL, "adminJohan");
		jndlProperties.put(Context.SECURITY_CREDENTIALS, "123456");
		jndlProperties.put("jboss.naming.client.ejb.context", true);
		
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
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosUsuario";
		String viewClassName = ServiciosUsuarioRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosUsuarioRemote serviciosUsuarios = null;
		try {
			serviciosUsuarios = (ServiciosUsuarioRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServiciosUsuario!datosInterface.ServiciosUsuarioRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosUsuarioRemote)serviciosUsuarios;
	}	
	
	
	public ServiciosProductoRemote getServicioProducto(String servicio) {
		ServiciosProductoRemote servicios = null;
		if(servicio.equals("producto")){
			servicios = getServiciosUsuarioConnect();
		}
		return (ServiciosProductoRemote) servicios;
	}

	
	public ServiciosProductoRemote getServiciosUsuarioConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosProducto";
		String viewClassName = ServiciosProductoRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosProductoRemote serviciosProductos = null;
		try {
			serviciosProductos = (ServiciosProductoRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServiciosProducto!datosInterface.ServiciosProductoRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosProductoRemote)serviciosProductos;
	}
	
	public ServiciosSucursalRemote getServicioSucursal(String servicio) {
		ServiciosSucursalRemote servicios = null;
		if(servicio.equals("sucursal")){
			servicios = getServiciosSucursalConnect();
		}
		return (ServiciosSucursalRemote) servicios;
	}

	
	public ServiciosSucursalRemote getServiciosSucursalConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosSucursal";
		String viewClassName = ServiciosSucursalRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosSucursalRemote serviciosSucursal = null;
		try {
			serviciosSucursal = (ServiciosSucursalRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServiciosSucursal!datosInterface.ServiciosSucursalRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosSucursalRemote)serviciosSucursal;
	}
	
	
	public ServiciosRepartidorRemote getServicioRepartidor(String servicio) {
		ServiciosRepartidorRemote servicios = null;
		if(servicio.equals("repartidor")){
			servicios = getServiciosRepartidorConnect();
		}
		return (ServiciosRepartidorRemote) servicios;
	}

	
	public ServiciosRepartidorRemote getServiciosRepartidorConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosRepartidor";
		String viewClassName = ServiciosRepartidorRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosRepartidorRemote serviciosRepartidor = null;
		try {
			serviciosRepartidor = (ServiciosRepartidorRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServiciosRepartidor!datosInterface.ServiciosRepartidorRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosRepartidorRemote)serviciosRepartidor;
	}
	
	public ServiciosPromocionRemote getServicioPromocion(String servicio) {
		ServiciosPromocionRemote servicios = null;
		if(servicio.equals("promocion")){
			servicios = getServiciosPromocionConnect();
		}
		return (ServiciosPromocionRemote) servicios;
	}

	
	public ServiciosPromocionRemote getServiciosPromocionConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosRepartidor";
		String viewClassName = ServiciosPromocionRemote.class.getName();
		
		System.out.println(viewClassName);
		
		ServiciosPromocionRemote serviciosRepartidor = null;
		try {
			serviciosRepartidor = (ServiciosPromocionRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServiciosPromocion!datosInterface.ServiciosPromocionRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServiciosPromocionRemote)serviciosRepartidor;
	}
	
	public ServicosPedidoRemote getServicioPedido(String servicio) {
		ServicosPedidoRemote servicios = null;
		if(servicio.equals("pedido")){
			servicios = getServiciosPedidoConnect();
		}
		return (ServicosPedidoRemote) servicios;
	}

	
	public ServicosPedidoRemote getServiciosPedidoConnect() {
		Context ctx = crearContexto();
		
		String namespace = "ejb:";
		String appName = nombreEAR;
		String moduleName = "ProyectoServiciosDatos";
		String beanName = "ServiciosRepartidor";
		String viewClassName = ServicosPedidoRemote.class.getName();
		
		System.out.println(viewClassName);
		ServicosPedidoRemote serviciosPedido = null;
		try {
			serviciosPedido = (ServicosPedidoRemote) ctx.lookup("ejb:ProyectoEARServiciosDatos/ProyectoServiciosDatos/ServicosPedido!datosInterface.ServicosPedidoRemote");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return (ServicosPedidoRemote)serviciosPedido;
	}
}

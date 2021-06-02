import API.RestClient;
import DTOs.VerificarTarjeta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

public class PagoBean implements Serializable {
	private VerificarTarjeta tarjeta;
	private String respuesta;
	private CarritoBean carritoBean;
	private DPedidoBean dPedidoBean;
	
	
	public PagoBean() {
		super();
		tarjeta = new VerificarTarjeta();
	}

	public VerificarTarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(VerificarTarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public CarritoBean getCarritoBean() {
		return carritoBean;
	}

	public void setCarritoBean(CarritoBean carritoBean) {
		this.carritoBean = carritoBean;
	}

	public DPedidoBean getdPedidoBean() {
		return dPedidoBean;
	}

	public void setdPedidoBean(DPedidoBean dPedidoBean) {
		this.dPedidoBean = dPedidoBean;
	}

	public String conexionAPI() {
		RestClient client = new RestClient();
		tarjeta.setValorCompra( carritoBean.getPedido().getPrecioTotal().floatValue());
		Response response = client.comprar(tarjeta);
		respuesta = response.readEntity(String.class);
		if(respuesta.equals("\"Compra rechazada\"")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("La transacción ha sido rechazada."));
		}else {
			//llevar pedido a la bd
			dPedidoBean.enviarPedido(carritoBean.getPedido());
			
			carritoBean.vaciarCarrito();
			return "Exito";
		}
		return "";
	}
	
}

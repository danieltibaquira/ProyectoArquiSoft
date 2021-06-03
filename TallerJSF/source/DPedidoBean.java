import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logicaInterfaz.LogicaPedidoRemote;
import model.Pedido;
import model.Producto;

public class DPedidoBean implements Serializable {
	
	ServiceLocator sl = new ServiceLocator();
	LogicaPedidoRemote serviciosPedido = sl.getLogicaPedido("pedido");
	
	private List<Pedido> pedidos;
	private DelegadoBean delegado;
	
	public DPedidoBean() {
		super();
		pedidos = new ArrayList<Pedido>();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
	
	public DelegadoBean getDelegado() {
		return delegado;
	}

	public void setDelegado(DelegadoBean delegado) {
		this.delegado = delegado;
	}

	public Pedido enviarPedido(Pedido pedido) {
		pedido.setFecha(new Date());
		pedido.setEstado(0);
		System.out.println("TIPO DE PAGO " +pedido.getTipoPago());
		Pedido respuesta = serviciosPedido.addPedido(pedido);
		return respuesta;
	}
	
	public void getPedidosUsuario() {
		pedidos = serviciosPedido.getPedidosUsuario(delegado.getUserFound());
		//System.out.println(pedidos.get(0).getIdPedido());
	}
	
	public String getLocationMessage() {
		return "https://www.mapquest.com/embed/near-4.7316991999999995,-74.0655104?center=4.7316991999999995,-74.0655104&zoom=15&maptype=map";
	}
}

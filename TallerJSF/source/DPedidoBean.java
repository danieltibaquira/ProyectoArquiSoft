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
		pedido.setDireccion("cra");
		pedido.setEstado(1);
		pedido.setSucursal(null);
		Pedido respuesta = serviciosPedido.addPedido(pedido);
		//delegado.getUserFound().addPedido(respuesta);
		//pedidos.add(respuesta);
		return respuesta;
	}
	
	public void getPedidosUsuario() {
		pedidos = serviciosPedido.getPedidosUsuario(delegado.getUserFound());
		//System.out.println(pedidos.get(0).getIdPedido());
	}
}

import java.io.Serializable;
import java.util.Date;

import logicaInterfaz.LogicaPedidoRemote;
import model.Pedido;
import model.Producto;

public class DPedidoBean implements Serializable {
	
	ServiceLocator sl = new ServiceLocator();
	LogicaPedidoRemote serviciosPedido = sl.getLogicaPedido("pedido");
	
	public void enviarPedido(Pedido pedido) {
		pedido.setFecha(new Date());
		pedido.setDireccion("cra");
		pedido.setEstado(1);
		pedido.setSucursal(null);
		serviciosPedido.addPedido(pedido);
	}
}

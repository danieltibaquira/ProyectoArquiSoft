import java.util.List;

import logicaInterfaz.LogicaPedidoRemote;
import logicaInterfaz.LogicaSucursalesRemote;
import model.Pedido;
import model.Sucursal;

public class DActualizarPedidoBean {
	ServiceLocator sl = new ServiceLocator();
	LogicaPedidoRemote servicioPedidos = sl.getLogicaPedido("pedido");
	
	public DActualizarPedidoBean()
	{
		super();
	}
	
	public Pedido updatePedido(Pedido pedido) {
		return servicioPedidos.updatePedido(pedido);
	}
	
	public List<Pedido> buscarPedidosSucursal(int idSucursal) {
		System.out.println("buscar pedidos por sucursal");
		System.out.println(servicioPedidos.getAllPedidosByIDSucursal(idSucursal).size());
		return servicioPedidos.getAllPedidosByIDSucursal(idSucursal);
	}
}

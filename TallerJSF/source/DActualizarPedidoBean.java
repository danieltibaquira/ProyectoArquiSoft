import java.util.List;

import logicaInterfaz.LogicaPedidoRemote;
import logicaInterfaz.LogicaRepartidorRemote;
import logicaInterfaz.LogicaSucursalesRemote;
import model.Pedido;
import model.Repartidor;
import model.Sucursal;

public class DActualizarPedidoBean {
	ServiceLocator sl = new ServiceLocator();
	LogicaPedidoRemote servicioPedidos = sl.getLogicaPedido("pedido");
	LogicaRepartidorRemote servicioRepartidor = sl.getLogicaRepartidor("repartidor");
	
	public DActualizarPedidoBean()
	{
		super();
	}
	
	public Pedido updatePedido(Pedido pedido) {
		return servicioPedidos.updatePedido(pedido);
	}
	
	public Repartidor updateRepartidor(Repartidor repartidor) {
		return servicioRepartidor.editRepartidor(repartidor);
	}
	
	public List<Pedido> buscarPedidosSucursal(int idSucursal) {
		System.out.println("buscar pedidos por sucursal");
		System.out.println(servicioPedidos.getAllPedidosByIDSucursal(idSucursal).size());
		return servicioPedidos.getAllPedidosByIDSucursal(idSucursal);
	}
	
	public List<Repartidor> buscarRepartidores() {
		System.out.println("buscando Repartidores");
		System.out.println("Repartidores disponibles:" + servicioRepartidor.getAllRepartidores().size());
		return servicioRepartidor.getAllRepartidores();
	}
}

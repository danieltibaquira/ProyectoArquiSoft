package logicaImpl;

import java.util.List;

import javax.ejb.Stateless;

import datosInterface.ServiciosSucursalRemote;
import datosInterface.ServicosPedidoRemote;
import logicaInterfaz.LogicaPedidoRemote;
import model.Pedido;
import model.Sucursal;
import model.Usuario;

/**
 * Session Bean implementation class LogicaPedido
 */
@Stateless
public class LogicaPedido implements LogicaPedidoRemote {

    /**
     * Default constructor. 
     */
    public LogicaPedido() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Pedido addPedido(Pedido pedido) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		Pedido sucursalDB = servicio.addPedido(pedido);
		return sucursalDB;
	}

	@Override
	public Pedido searchPedido(Pedido pedido) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		Pedido sucursalDB = servicio.searchPedido(pedido);
		return sucursalDB;
	}

	@Override
	public Pedido updatePedido(Pedido pedido) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		Pedido pedidolDB = servicio.updatePedido(pedido);
		return pedidolDB;
	}
	
	@Override
	public List<Pedido> getAllPedidos() {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		List<Pedido> pedidos = servicio.getAllPedidos();
		return pedidos;
	}

	@Override
	public List<Pedido> getPedidosUsuario(Usuario usuario) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		List<Pedido> pedidos = servicio.getPedidosUsuario(usuario);
		return pedidos;
	}
	public List<Pedido> getAllPedidosByIDSucursal(int idSucursal) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		List<Pedido> pedidos = servicio.getAllPedidosByIDSucursal(idSucursal);
	
	@Override
	public List<Pedido> getAllPedidosByIDRepartidor(int idRepartidor) {
		ServiceLocator sl = new ServiceLocator();
		ServicosPedidoRemote servicio = sl.getServicioPedido("pedido");
		List<Pedido> pedidos = servicio.getAllPedidosByIDRepartidor(idRepartidor);
		return pedidos;
	}

}

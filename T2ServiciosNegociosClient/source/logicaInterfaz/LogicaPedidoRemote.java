package logicaInterfaz;

import java.util.List;

import javax.ejb.Remote;

import model.Pedido;

@Remote
public interface LogicaPedidoRemote {
	public Pedido addPedido(Pedido pedido);
	public Pedido searchPedido(Pedido pedido);
	public List<Pedido> getAllPedidos();

}

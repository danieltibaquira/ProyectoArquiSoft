package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Pedido;

@Remote
public interface ServicosPedidoRemote {
	public Pedido addPedido(Pedido pedido);
	public Pedido searchPedido(Pedido pedido);
	public Pedido updatePedido(Pedido pedido);
	public List<Pedido> getAllPedidos();
	public List<Pedido> getAllPedidosByIDSucursal(int idSucursal);
}

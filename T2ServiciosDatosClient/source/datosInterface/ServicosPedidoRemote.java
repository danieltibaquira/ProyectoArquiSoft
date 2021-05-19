package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Pedido;
import model.Sucursal;

@Remote
public interface ServicosPedidoRemote {
	public Pedido addPedido(Pedido pedido);
	public Pedido searchPedido(Pedido pedido);
	public List<Pedido> getAllPedidos();
}

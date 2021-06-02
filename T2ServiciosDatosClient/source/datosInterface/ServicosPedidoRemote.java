package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Pedido;
import model.Sucursal;
import model.Usuario;

@Remote
public interface ServicosPedidoRemote {
	public Pedido addPedido(Pedido pedido);
	public Pedido searchPedido(Pedido pedido);
	public List<Pedido> getAllPedidos();
	public List<Pedido> getPedidosUsuario(Usuario usuario);
}

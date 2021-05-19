package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Producto;

@Remote
public interface ServiciosProductoRemote {
	public Producto addProducto(Producto product);
	public Producto searchProducto(Producto product);
	public List<Producto> getAllProductos();
	public boolean deleteProducto(Integer id);
}

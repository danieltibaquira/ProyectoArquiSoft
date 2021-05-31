package logicaImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import datosInterface.ServiciosProductoRemote;
import datosInterface.ServiciosUsuarioRemote;
import logicaInterfaz.logicaProductosRemote;
import model.Producto;

/**
 * Session Bean implementation class logicaProductos
 */
@Stateless
@LocalBean
public class logicaProductos implements logicaProductosRemote {

    /**
     * Default constructor. 
     */
    public logicaProductos() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Producto addProducto(Producto product) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosProductoRemote servicio = sl.getServicioProducto("producto");
		Producto productDB = servicio.addProducto(product);
		return productDB;
	}
	
	@Override
	public Producto editProducto(Producto product) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosProductoRemote servicio = sl.getServicioProducto("producto");
		Producto productDB = servicio.editProducto(product);
		return productDB;
	}

	@Override
	public boolean deleteProducto(Integer id) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosProductoRemote servicio = sl.getServicioProducto("producto");
		boolean res = servicio.deleteProducto(id);
		return false;
	}

	@Override
	public List<Producto> getAllProductos() {
		ServiceLocator sl = new ServiceLocator();
		ServiciosProductoRemote servicio = sl.getServicioProducto("producto");
		List<Producto> products = servicio.getAllProductos();
		return products;
	}

	@Override
	public Producto searchProducto(Producto product) {
		ServiceLocator sl = new ServiceLocator();
		ServiciosProductoRemote servicio = sl.getServicioProducto("producto");
		Producto productDB = servicio.searchProducto(product);
		return productDB;
	}

}

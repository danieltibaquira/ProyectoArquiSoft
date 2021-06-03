package logicaInterfaz;

import java.util.List;

import javax.ejb.Remote;

import model.Sucursal;

@Remote
public interface LogicaSucursalesRemote {
	public Sucursal addSucursal(Sucursal sucursal);
	public Sucursal updateSucursal(Sucursal sucursal);
	//public Sucursal updateSucursal2(String nombre, String password, String direccion, String telefono);
	public Sucursal searchSucursal(Sucursal sucursal);
	public List<Sucursal> getAllSucursales();
	public boolean deleteSucursal(Integer id);
}

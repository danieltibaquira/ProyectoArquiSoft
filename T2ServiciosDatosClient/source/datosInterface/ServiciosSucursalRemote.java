package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Sucursal;

@Remote
public interface ServiciosSucursalRemote {
	public Sucursal addSucursal(Sucursal sucursal);
	public Sucursal searchSucursal(Sucursal sucursal);
	public List<Sucursal> getAllSucursales();
	public boolean deleteSucursal(Integer id);

}

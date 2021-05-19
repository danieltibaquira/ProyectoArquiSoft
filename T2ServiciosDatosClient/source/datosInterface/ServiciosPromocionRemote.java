package datosInterface;

import java.util.List;

import javax.ejb.Remote;

import model.Promocion;
import model.Sucursal;

@Remote
public interface ServiciosPromocionRemote {
	public Promocion addPromocion(Promocion promocion);
	public Promocion searchPromocion(Promocion promocion);
	public List<Promocion> getAllPromociones();
	public boolean deletePromocion(Integer id);
}

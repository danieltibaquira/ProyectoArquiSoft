package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Sucursal database table.
 * 
 */
@Entity
@NamedQuery(name="Sucursal.findAll", query="SELECT s FROM Sucursal s")
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sucursal")
	private int idSucursal;

	@Column(name="contrasena_sucursal")
	private String contrasenaSucursal;

	@Column(name="direccion_sucursal")
	private String direccionSucursal;

	@Column(name="foto_sucursal")
	private String fotoSucursal;

	@Column(name="nombre_sucursal")
	private String nombreSucursal;

	private String telefono;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="sucursal")
	private List<Pedido> pedidos;

	//bi-directional many-to-one association to Repartidor
	@OneToMany(mappedBy="sucursal")
	private List<Repartidor> repartidors;

	public Sucursal() {
	}

	public int getIdSucursal() {
		return this.idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getContrasenaSucursal() {
		return this.contrasenaSucursal;
	}

	public void setContrasenaSucursal(String contrasenaSucursal) {
		this.contrasenaSucursal = contrasenaSucursal;
	}

	public String getDireccionSucursal() {
		return this.direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getFotoSucursal() {
		return this.fotoSucursal;
	}

	public void setFotoSucursal(String fotoSucursal) {
		this.fotoSucursal = fotoSucursal;
	}

	public String getNombreSucursal() {
		return this.nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setSucursal(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setSucursal(null);

		return pedido;
	}

	public List<Repartidor> getRepartidors() {
		return this.repartidors;
	}

	public void setRepartidors(List<Repartidor> repartidors) {
		this.repartidors = repartidors;
	}

	public Repartidor addRepartidor(Repartidor repartidor) {
		getRepartidors().add(repartidor);
		repartidor.setSucursal(this);

		return repartidor;
	}

	public Repartidor removeRepartidor(Repartidor repartidor) {
		getRepartidors().remove(repartidor);
		repartidor.setSucursal(null);

		return repartidor;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Repartidor database table.
 * 
 */
@Entity
@NamedQuery(name="Repartidor.findAll", query="SELECT r FROM Repartidor r")
public class Repartidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_repartidor")
	private int idRepartidor;

	@Column(name="foto_repartidor")
	private String fotoRepartidor;

	private BigDecimal latitude;

	private BigDecimal longitude;

	@Column(name="nombre_repartidor")
	private String nombreRepartidor;
	private String numero;

	private String password;

	//bi-directional many-to-one association to Pedido
	//@OneToMany(mappedBy="repartidor")
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "repartidor", cascade = CascadeType.ALL)
	private List<Pedido> pedidos;
	private Sucursal sucursal;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="Sucursal_id_sucursal")
	private Sucursal sucursal;

	public Repartidor() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_repartidor")
	public int getIdRepartidor() {
		return this.idRepartidor;
	}

	public void setIdRepartidor(int idRepartidor) {
		this.idRepartidor = idRepartidor;
	}

	public String getFotoRepartidor() {
		return this.fotoRepartidor;
	}

	public void setFotoRepartidor(String fotoRepartidor) {
		this.fotoRepartidor = fotoRepartidor;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getNombreRepartidor() {
		return this.nombreRepartidor;
	}

	public void setNombreRepartidor(String nombreRepartidor) {
		this.nombreRepartidor = nombreRepartidor;
	}


	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setRepartidor(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setRepartidor(null);

		return pedido;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
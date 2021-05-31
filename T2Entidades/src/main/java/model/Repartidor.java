package model;

import java.io.Serializable;
import javax.persistence.*;
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

	@Column(name="nombre_repartidor")
	private String nombreRepartidor;

	private String numero;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="repartidor")
	private List<Pedido> pedidos;

	public Repartidor() {
	}

	public int getIdRepartidor() {
		return this.idRepartidor;
	}

	public void setIdRepartidor(int idRepartidor) {
		this.idRepartidor = idRepartidor;
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

}
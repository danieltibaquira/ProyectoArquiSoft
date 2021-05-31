package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;

	private String descripcion;

	@Column(name="nombre_producto")
	private String nombreProducto;

	private BigDecimal precio;

	//bi-directional many-to-many association to Pedido
	@ManyToMany
	@JoinTable(
		name="Pedido_has_Producto"
		, joinColumns={
			@JoinColumn(name="Producto_id_producto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Pedido_id_pedido")
			}
		)
	private List<Pedido> pedidos;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="Pedido_id_pedido")
	private Pedido pedido;

	//bi-directional many-to-one association to Promocion
	@ManyToOne
	@JoinColumn(name="Promocion_id_promocion")
	private Promocion promocion;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Promocion getPromocion() {
		return this.promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

}
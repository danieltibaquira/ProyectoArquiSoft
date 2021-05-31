package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	private String direccion;

	private int estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="precio_total")
	private BigDecimal precioTotal;

	@Column(name="tipo_pago")
	private int tipoPago;

	//bi-directional many-to-one association to Repartidor
	@ManyToOne
	@JoinColumn(name="Repartidor_id_repartidor")
	private Repartidor repartidor;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="Sucursal_id_sucursal")
	private Sucursal sucursal;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_id_usuario")
	private Usuario usuario;

	//bi-directional many-to-many association to Producto
	@ManyToMany(mappedBy="pedidos")
	private List<Producto> productos1;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="pedido")
	private List<Producto> productos2;

	public Pedido() {
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Repartidor getRepartidor() {
		return this.repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos1() {
		return this.productos1;
	}

	public void setProductos1(List<Producto> productos1) {
		this.productos1 = productos1;
	}

	public List<Producto> getProductos2() {
		return this.productos2;
	}

	public void setProductos2(List<Producto> productos2) {
		this.productos2 = productos2;
	}

	public Producto addProductos2(Producto productos2) {
		getProductos2().add(productos2);
		productos2.setPedido(this);

		return productos2;
	}

	public Producto removeProductos2(Producto productos2) {
		getProductos2().remove(productos2);
		productos2.setPedido(null);

		return productos2;
	}

}
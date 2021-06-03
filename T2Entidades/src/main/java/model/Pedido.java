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
	@ManyToOne(cascade = CascadeType.PERSIST)
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
	private List<Producto> productos;

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

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
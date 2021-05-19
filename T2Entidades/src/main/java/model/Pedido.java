package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the Pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pedido")
	private int idPedido;

	private String direccion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="precio_total")
	private BigDecimal precioTotal;

	@Column(name="tipo_pago")
	private int tipoPago;

	@Column(name="Usuario_id_usuario")
	private int usuario_id_usuario;

	//bi-directional many-to-one association to Repartidor
	@ManyToOne
	@JoinColumn(name="Repartidor_id_repartidor")
	private Repartidor repartidor;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="Sucursal_id_sucursal")
	private Sucursal sucursal;

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

	public int getUsuario_id_usuario() {
		return this.usuario_id_usuario;
	}

	public void setUsuario_id_usuario(int usuario_id_usuario) {
		this.usuario_id_usuario = usuario_id_usuario;
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

}
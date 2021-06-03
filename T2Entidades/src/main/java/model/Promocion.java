package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Promocion database table.
 * 
 */
@Entity
@NamedQuery(name="Promocion.findAll", query="SELECT p FROM Promocion p")
public class Promocion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_promocion")
	private int idPromocion;

	private String ciudad;

	private String descripcion;

	private double descuento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="tipo_promocion")
	private int tipoPromocion;

	@Column(name="zona_validez")
	private String zonaValidez;

	//bi-directional many-to-one association to Producto
	@OneToMany(fetch = FetchType.EAGER, mappedBy="promocion")
	private List<Producto> productos;

	public Promocion() {
	}

	public int getIdPromocion() {
		return this.idPromocion;
	}

	public void setIdPromocion(int idPromocion) {
		this.idPromocion = idPromocion;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getTipoPromocion() {
		return this.tipoPromocion;
	}

	public void setTipoPromocion(int tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public String getZonaValidez() {
		return this.zonaValidez;
	}

	public void setZonaValidez(String zonaValidez) {
		this.zonaValidez = zonaValidez;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setPromocion(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setPromocion(null);

		return producto;
	}

}
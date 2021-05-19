package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="Producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id_producto;

	private String descripcion;

	private String nombre_producto;

	private int precio;

	public Producto() {
	}

	public int getIdProducto() {
		return this.id_producto;
	}

	public void setIdProducto(int idProducto) {
		this.id_producto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre_producto;
	}

	public void setNombre(String nombre) {
		this.nombre_producto = nombre;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="Usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id_usuario;

	private String apellido_usuario;

	private String correo;

	private String nombre_usuario;

	private String contrasena;

	private String username;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.id_usuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.id_usuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellido_usuario;
	}

	public void setApellidos(String apellidos) {
		this.apellido_usuario = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return this.nombre_usuario;
	}

	public void setNombres(String nombres) {
		this.nombre_usuario = nombres;
	}

	public String getPassword() {
		return this.contrasena;
	}

	public void setPassword(String password) {
		this.contrasena = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
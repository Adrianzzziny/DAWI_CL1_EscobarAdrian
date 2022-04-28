package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tema")
public class Tema {
	@Id
	private int idTema;
	private String nombre;

	@Override
	public String toString() {
		return "Tema [idTema=" + idTema + ", nombre=" + nombre + "]";
	}

	public Tema() {
		super();
	}

	public Tema(int idTema, String nombre) {
		super();
		this.idTema = idTema;
		this.nombre = nombre;
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

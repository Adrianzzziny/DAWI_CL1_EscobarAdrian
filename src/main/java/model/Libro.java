package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "Libro")

public class Libro {
	
	@Column(name = "idLibro")
	@Id
	private int idlibro; //cod_usuario
	
	@Column(name = "titulo")
	private String tituloLibro;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "cantEjemplares")
	private int cantEjemplares;
	
	@Column(name = "origen")
	private String origen;
	
	@Column(name = "idTema")
	private int idTema;

	@Override
	public String toString() {
		return "Libro [idlibro=" + idlibro + ", tituloLibro=" + tituloLibro + ", precio=" + precio + ", cantEjemplares="
				+ cantEjemplares + ", origen=" + origen + ", idTema=" + idTema + "]";
	}

	public Libro() {
		super();
	}

	public Libro(int idlibro, String tituloLibro, double precio, int cantEjemplares, String origen, int idTema) {
		super();
		this.idlibro = idlibro;
		this.tituloLibro = tituloLibro;
		this.precio = precio;
		this.cantEjemplares = cantEjemplares;
		this.origen = origen;
		this.idTema = idTema;
	}

	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantEjemplares() {
		return cantEjemplares;
	}

	public void setCantEjemplares(int cantEjemplares) {
		this.cantEjemplares = cantEjemplares;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	
	
	
}

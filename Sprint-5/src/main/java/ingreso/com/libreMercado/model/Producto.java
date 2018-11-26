package ingreso.com.libreMercado.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Producto {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombreProducto;
	private float precioProducto;
	private int cantidad;
	private float precioPorCantidad;
	private String imagen;
	private String tags;

	public Producto(String nombreProducto, float precioPorCantidad, int cantidad, String imagen) {
		this.nombreProducto = nombreProducto;
		this.precioPorCantidad = precioPorCantidad;
		this.cantidad = cantidad;
		this.imagen = imagen;

	}

	public Producto() {
	}

	@ManyToOne
	@JoinColumn(name = "vendedor", referencedColumnName = "nombreDeUsuario", updatable = false)
	private Usuario owner;

	public Producto(Usuario owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public float getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getImagen() { return imagen; }
	public void setImagen(String imagen) { this.imagen = imagen; }

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags(){
		return tags;
	}

	public float getPrecioPorCantidad() {
		return precioPorCantidad;
	}
	public void setPrecioPorCantidad(float precioPorCantidad) {
		this.precioPorCantidad = precioPorCantidad;
	}
		
	public void precioPorCantidad(){
		float precioFinal = (this.precioProducto * this.cantidad);
		
		this.precioPorCantidad = precioFinal;
	}

	public Usuario getOwner() {
		return this.owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

}

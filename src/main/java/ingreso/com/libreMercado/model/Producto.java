package ingreso.com.libreMercado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	

	
}

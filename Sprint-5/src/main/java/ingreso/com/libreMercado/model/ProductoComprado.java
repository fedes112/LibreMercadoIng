package ingreso.com.libreMercado.model;

import javax.persistence.*;

@Entity
public class ProductoComprado {

	@Id
	@GeneratedValue
	private int id;

	private String nombreProducto;
	private float precioProducto;
	private String imagen;


	@ManyToOne
	@JoinColumn(name = "Vendedor", referencedColumnName = "nombreDeUsuario", updatable = false)
	private Usuario vendedor;

	@ManyToOne
	@JoinColumn(name = "comprador", referencedColumnName = "nombreDeUsuario", updatable = false)
	private Usuario owner;


	public ProductoComprado() {	}

	public ProductoComprado(Usuario owner) {
		this.owner = owner;
	}

	public ProductoComprado(String nombreProducto, String imagen,float precioProducto,Usuario vendedor,Usuario owner) {
		this.nombreProducto = nombreProducto;
		this.imagen = imagen;
		this.precioProducto = precioProducto;
		this.vendedor = vendedor;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) { this.id = id; }

	public String getNombreProducto() { return nombreProducto; }
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public float getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getImagen() { return imagen; }
	public void setImagen(String imagen) { this.imagen = imagen; }

	public Usuario getVendedor() { return vendedor; }
	public void setVendedor(Usuario vendedor) { this.vendedor = vendedor; }


	public Usuario getOwner() {
		return this.owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

}

package ingreso.com.libreMercado.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @Column(length = 190)
    private String nombreDeUsuario;

    private long dni;
    private String contraseña;
    private Boolean esAdministrador;
    private String mail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private List<Producto> historialCompra = new ArrayList<Producto>();

    public Usuario(){}


    public Usuario(String nombreACrear, String contraseñaACrear, long dni, String mail){
        setNombreDeUsuario(nombreACrear);
        setContraseña(contraseñaACrear);
        setDni(dni);
        setMail(mail);

    }

    public Boolean getEsAdministrador(){
        return esAdministrador;
    }

    public void setEsAdministrador(Boolean valor){
        this.esAdministrador = valor;
    }
    public String getContraseña() {
        return contraseña;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean chequearUsuario(String nombreProveninente){
        return (this.nombreDeUsuario.equals(nombreProveninente));

    }

    public Boolean chequearPassWord(String contraseñaProveniente){
        return (this.contraseña.equals(contraseñaProveniente));

    }

    public void agregarProductoAlHistorial(Producto producto){
        this.historialCompra.add(producto);
    }

    public List<Producto> getHistorial(){
        return this.historialCompra;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
}

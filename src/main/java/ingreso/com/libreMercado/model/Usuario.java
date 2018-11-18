package ingreso.com.libreMercado.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    private long dni;

    private String nombreDeUsuario;
    private String contraseña;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private List<Producto> historialCompra = new ArrayList<Producto>();

    public Usuario(){}


    public Usuario(String nombreACrear, String contraseñaACrear, long dni){
        setNombreDeUsuario(nombreACrear);
        setContraseña(contraseñaACrear);
        setDni(dni);
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

    public Boolean chequearPassWords(String contraseñaProveniente){
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
}

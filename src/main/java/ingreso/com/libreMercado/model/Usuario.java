package ingreso.com.libreMercado.model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    private String nombreDeUsuario;

    private long dni;
    private String contraseña;
    private Boolean esAdministrador;

    public Usuario(){}


    public Usuario(String nombreACrear, String contraseñaACrear, long dni){
        setNombreDeUsuario(nombreACrear);
        setContraseña(contraseñaACrear);
        setDni(dni);

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

    public Boolean chequearPassWords(String contraseñaProveniente){
        return (this.contraseña.equals(contraseñaProveniente));

    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }
}

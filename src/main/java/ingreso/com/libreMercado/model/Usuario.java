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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> puntuacion = new ArrayList<Integer>();

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

    public void puntuar(Integer puntuacion){ this.puntuacion.add(puntuacion) ;}

    public int puntuacion() {
        if(puntuacion.size() == 0) return 0;
        else {
            int puntos = 0;
            for (Integer punto: this.puntuacion) {
                puntos += punto;
            }
            return puntos / puntuacion.size();
        }
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

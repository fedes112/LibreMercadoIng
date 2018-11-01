package ingreso.com.libreMercado;

import ingreso.com.libreMercado.model.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestUsuarioModelo {



    @Test
    public void testeoChequeoDeUsuario(){
        Usuario userExistente = new Usuario("Pepe","Papas");

        Assert.assertTrue(userExistente.chequearUsuario("Pepe"));
        Assert.assertTrue(userExistente.chequearPassWords("Papas"));
    }
}

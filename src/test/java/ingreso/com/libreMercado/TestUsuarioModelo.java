package ingreso.com.libreMercado;

import ingreso.com.libreMercado.model.DaoProducto;
import ingreso.com.libreMercado.model.DaoUsuario;
import ingreso.com.libreMercado.model.Producto;
import ingreso.com.libreMercado.model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsuarioModelo {

    @Autowired
    private DaoUsuario daoUsuario;

    @Autowired
    private DaoProducto daoProducto;

    Usuario user = new Usuario("User","111",555, "user@gmail");

    @Test
    public void testeoChequeoDeUsuario(){
        Usuario usuarioGuido = new Usuario("Guido","1234",111, "usuarioguido@gmail");

        Assert.assertTrue(usuarioGuido.chequearUsuario("Guido"));
        Assert.assertTrue(usuarioGuido.chequearPassWord("1234"));
    }

    @Test
    public void testUsuarioSeRegistraCorrectamente(){
        Usuario usuarioJuan = new Usuario("Juan","1234",111, "usuarioJuan@gmail");
        daoUsuario.save(usuarioJuan);

        Assert.assertTrue(daoUsuario.exists("Juan"));
    }

    @Test
    public void testSeRealizaUnaPublicacion() {
        daoUsuario.save(user);
        Producto computadora_Asus = new Producto("Computadora Asus", 1000, 5, null);
        computadora_Asus.setOwner(user);
        daoProducto.save(computadora_Asus);

        Assert.assertTrue(daoProducto.existsByNombreProducto("Computadora Asus"));
    }

    @After
    public  void eliminarBD(){
        daoProducto.deleteAll();
    }

}

package ingreso.com.libreMercado;

import ingreso.com.libreMercado.model.Producto;
import ingreso.com.libreMercado.model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductoModelo {

    @Test
    public void testeoChequeoDeProducto(){
        Producto computadora = new Producto("Computadora", 500, 2, null);

        Assert.assertEquals(computadora.getNombreProducto(), "Computadora");
        Assert.assertEquals(computadora.getPrecioPorCantidad(), 500,0);
        Assert.assertEquals(computadora.getCantidad(), 2);

    }

}

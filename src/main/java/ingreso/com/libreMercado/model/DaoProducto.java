package ingreso.com.libreMercado.model;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DaoProducto extends CrudRepository<Producto, Integer>{

    ArrayList<Producto> findByNombreProductoLike(String nombreProducto);  //OrPrecioProducto
    ArrayList<Producto> findByTagsLike (String tag);
	
}

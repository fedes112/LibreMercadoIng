package ingreso.com.libreMercado.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface DaoProducto extends CrudRepository<Producto, Integer>{

    ArrayList<Producto> findByNombreProductoLike(String nombreProducto);  //OrPrecioProducto
    ArrayList<Producto> findByTagsLike (String tag);
    Producto findByNombreProducto(String nombreProducto);
    Boolean existsByNombreProducto(String nombreProducto);
    ArrayList<Producto> findByOwner (Usuario creador);
    
    @Query("SELECT p FROM Producto p WHERE p.owner != :user")
    ArrayList<Producto> findAllForUser(@Param("user") Usuario user);
}

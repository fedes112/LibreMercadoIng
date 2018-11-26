package ingreso.com.libreMercado.model;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DaoProductoComprado extends CrudRepository<ProductoComprado, Integer>{

    ArrayList<ProductoComprado> findByOwner(Usuario creador);

}

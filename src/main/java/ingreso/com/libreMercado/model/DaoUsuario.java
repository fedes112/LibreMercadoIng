package ingreso.com.libreMercado.model;

import org.springframework.data.repository.CrudRepository;


public interface DaoUsuario extends CrudRepository<Usuario, String> {
	
	Usuario findByNombreDeUsuario(String nombre);

}

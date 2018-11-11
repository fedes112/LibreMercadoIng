package ingreso.com.libreMercado.model;

import org.springframework.data.repository.CrudRepository;


public interface DaoUsuario extends CrudRepository<Usuario, Long> {
	
	Usuario findByNombreDeUsuario(String nombre);

}

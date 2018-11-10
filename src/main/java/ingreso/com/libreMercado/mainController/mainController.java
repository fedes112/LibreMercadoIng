package ingreso.com.libreMercado.mainController;

import ingreso.com.libreMercado.model.DaoUsuario;
import ingreso.com.libreMercado.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ingreso.com.libreMercado.model.DaoProducto;
import ingreso.com.libreMercado.model.Producto;

import javax.servlet.http.HttpSession;

@Controller
public class mainController {

	@Autowired
	private DaoProducto daoProducto;
	@Autowired
	private DaoUsuario daoUsuario;

	//Home
	@RequestMapping(value = "/",
			method = RequestMethod.GET)
	public ModelAndView inicioGet(){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("inicio");

		return modelAndView;
	}

	@RequestMapping(value = "/menuPrincipal",
			method = RequestMethod.GET)
	public ModelAndView menuPrincipalGet(){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("menuPrincipal");

		return modelAndView;
	}


	@RequestMapping(value= "agregarProducto",
			method = RequestMethod.GET)
	public ModelAndView agregarProductoGET(){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("Producto", new Producto());

		modelAndView.setViewName("agregarProducto");

		return modelAndView;

	}

	@RequestMapping(value= "agregarProducto",
			method = RequestMethod.POST)
	public ModelAndView agregarProductoPOST(@ModelAttribute Producto producto){

		ModelAndView modelAndView = new ModelAndView();

		producto.precioPorCantidad();
		daoProducto.save(producto);

		modelAndView.addObject("producto", new Producto());

		modelAndView.setViewName("redirect:/agregarProducto.html");

		return modelAndView;
	}

	@RequestMapping(value= "verProductos",
			method = RequestMethod.GET)
	public ModelAndView mostrarProductos(HttpSession session){

		ModelAndView modelAndView = new ModelAndView();

		if(null != session.getAttribute("usuario")) {
			modelAndView.addObject("listaDeProductos", daoProducto.findAll());
			modelAndView.setViewName("verProductos");
		}
		else {
			modelAndView.setViewName("inicio");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/eliminarProducto",
			method = RequestMethod.GET)
	public ModelAndView eliminarRecetaGET(@RequestParam("id") int id){
		//Producto producto  = daoProducto.findOne(id);

		daoProducto.delete(id);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("listaDeProductos", daoProducto.findAll());
		modelAndView.setViewName("verProductos");

		return modelAndView;

	}

	@RequestMapping(value = "/comprarProducto",
			method = RequestMethod.GET)
	public ModelAndView comprarProductoGET(@RequestParam("id") int id){
		//Producto producto  = daoProducto.findOne(id);

		ModelAndView modelAndView = new ModelAndView();

		Producto producto = daoProducto.findOne(id);

		producto.setCantidad(producto.getCantidad() - 1);
		daoProducto.save(producto);

		if(producto.getCantidad() <= 0) {
			daoProducto.delete(id);
		}

		modelAndView.addObject("listaDeProductos", daoProducto.findAll());
		modelAndView.setViewName("verProductos");

		return modelAndView;
	}

	@RequestMapping(value= "registrarUsuario",
			method = RequestMethod.GET)
	public ModelAndView registrarUsuarioGET(){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("Usuario", new Usuario());

		modelAndView.setViewName("registrarUsuario");

		return modelAndView;

	}

	@RequestMapping(value= "registrarUsuario",
			method = RequestMethod.POST)
	public ModelAndView registrarUsuarioPOST(@ModelAttribute Usuario usuario){

		ModelAndView modelAndView = new ModelAndView();

		daoUsuario.save(usuario);

		modelAndView.addObject("usuario", new Usuario());

		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}

	@RequestMapping(value = "inicioSesion",
			method = RequestMethod.GET)
	public ModelAndView formularioInicioUsuarioGet(){
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("usuario", new Usuario());

		modelAndView.setViewName("inicioSesion");

		return modelAndView;
	}

	//Se verifica si un propietario existe o no en la base de datos
	@RequestMapping(value = "verificarUsuario",
			method = RequestMethod.POST)
	public ModelAndView formularioInicioUsuarioPost(@ModelAttribute Usuario usuario, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (daoUsuario.exists(usuario.getDni())) {

			Usuario usuario1 = daoUsuario.findOne(usuario.getDni());

			if (usuario.getContraseña().equals(usuario1.getContraseña())) {
				modelAndView.addObject("usuario", usuario1);
				modelAndView.setViewName("menuPrincipal");

				session.setAttribute("usuario", usuario1);

			} else {
				
				modelAndView.setViewName("errorInicioSesion");
			}

		}else{
			modelAndView.setViewName("errorInicioSesion");
		}
		return modelAndView;
	}


}

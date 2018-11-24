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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class mainController {


	@Autowired
	private DaoProducto daoProducto;
	@Autowired
	private DaoUsuario daoUsuario;
	private ArrayList<String> tags = new ArrayList<String>();


	@RequestMapping(value = "/",
			method = RequestMethod.GET)
	public ModelAndView inicioGet(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("inicio");

		return modelAndView;
	}

	@RequestMapping(value = "/menuPrincipal",
			method = RequestMethod.GET)
	public ModelAndView menuPrincipalGet(HttpSession session) {

		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		return this.iniciarSesion(usuario, modelAndView);
	}

	@RequestMapping(value= "agregarProducto",
			method = RequestMethod.GET)
	public ModelAndView agregarProductoGET(HttpSession session) {

		this.creacionDeTags();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listadeTags",tags);

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if(null != usuario && !usuario.getEsAdministrador()) {
			modelAndView.addObject("Producto", new Producto());
			modelAndView.setViewName("agregarProducto");
		}
		else {
			modelAndView.setViewName("inicio");
		}
		return modelAndView;

	}

	@RequestMapping(value= "agregarProducto",
			method = RequestMethod.POST)
	public ModelAndView agregarProductoPOST(@ModelAttribute Producto producto, HttpSession session){

		ModelAndView modelAndView = new ModelAndView();

		producto.precioPorCantidad();
		producto.setOwner((Usuario) session.getAttribute("usuario"));

		System.out.println("------------------------------------------------ Usuario: " + ((Usuario) session.getAttribute("usuario")).getNombreDeUsuario());

		daoProducto.save(producto);
		modelAndView.addObject("producto", new Producto());
     	modelAndView.setViewName("redirect:/agregarProducto.html");

		return modelAndView;
	}

	@RequestMapping(value = "/eliminarProducto",
			method = RequestMethod.GET)
	public ModelAndView eliminarProductoGET(@RequestParam("id") int id){

		daoProducto.delete(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listaDeProductos", daoProducto.findAll());
		modelAndView.setViewName("verProductos");

		return modelAndView;

	}

	@RequestMapping(value = "/comprarProducto",
			method = RequestMethod.GET)
	public ModelAndView comprarProductoGET(HttpSession session ,@RequestParam("id") int id){
		//Producto producto  = daoProducto.findOne(id);

		Usuario userSes = (Usuario) session.getAttribute("usuario");
		ModelAndView modelAndView = new ModelAndView();
		Producto producto = daoProducto.findOne(id);

		this.comprarProducto(producto, userSes, id);

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

		usuario.setEsAdministrador(false);
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

		this.verificarUsuario(usuario, modelAndView, session);

		return this.verificarUsuario(usuario, modelAndView, session);
	}

	@RequestMapping(value = "cerrarSesion",
			method = RequestMethod.GET)
	public ModelAndView cerrarSesionGet(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		session.invalidate();
		modelAndView.setViewName("inicio");

		return modelAndView;
	}

	@RequestMapping(value= "verProductos",
			method = RequestMethod.GET)
	public ModelAndView mostrarProductos(HttpSession session ,@RequestParam(value = "nombreProducto", required = false) String nombreProducto){
		ModelAndView modelAndView = new ModelAndView();
		Usuario user = (Usuario) session.getAttribute("usuario");
		return this.verProductos(nombreProducto, modelAndView, session);

	}		

	@RequestMapping(value= "verUsuarios",
			method = RequestMethod.GET)
	public ModelAndView verUsuarios(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		return this.verTotalidadDeUsuarios(modelAndView, usuario);
		
	}

	@RequestMapping(value = "/eliminarUsuario",
			method = RequestMethod.GET)
	public ModelAndView eliminarUsuarioGET(@RequestParam("id") String id, HttpSession session){

		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");


		return this.eliminarUsuario(usuario, modelAndView, id);

	}
	
	@RequestMapping(value= "verTodosLosProductos",
			method = RequestMethod.GET)
	public ModelAndView verTodosLosProductos(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Iterable<Producto> listaProductos = daoProducto.findAll();
		
		return this.verTotalidadDeProductos(usuario, modelAndView, listaProductos);
	}

	
	@RequestMapping(value = "/historial",
			method = RequestMethod.GET)
	public ModelAndView historialGet(HttpSession session){

		Usuario userSes = (Usuario) session.getAttribute("usuario");
		List<Producto> historial ;
		ModelAndView modelAndView = new ModelAndView();
		historial = userSes.getHistorial();
		modelAndView.addObject("historial", historial);
		modelAndView.setViewName("/historial");

		return modelAndView;
	}


	@RequestMapping(value = "/verCreados",
			method = RequestMethod.GET)
	public ModelAndView productosCreadosGet(HttpSession session){

		Usuario userSes = (Usuario) session.getAttribute("usuario");
		List<Producto>  creados;
		ModelAndView modelAndView = new ModelAndView();
		creados = daoProducto.findByOwner(userSes);
		modelAndView.addObject("creaciones", creados);
		modelAndView.setViewName("/verCreados");

		return modelAndView;
	}

	
	
	//----------------------METODOS-----------------------
	
	public ModelAndView iniciarSesion(Usuario usuario, ModelAndView modelAndView) {
		if(null != usuario) {
			if (usuario.getEsAdministrador()) {
				modelAndView.setViewName("menuAdmin");
			}
			else {
				modelAndView.setViewName("menuPrincipal");
			}
		}
		else {
			modelAndView.setViewName("inicio");
		}
		return modelAndView;
	}
	
	public void creacionDeTags() {
		tags.clear();
		tags.add("Computacion");
		tags.add("Deporte");
		tags.add("Ocio");
	}
	
	public void comprarProducto(Producto producto, Usuario userSes, int id) {
		producto.setCantidad(producto.getCantidad() - 1);
		userSes.agregarProductoAlHistorial(producto);
		daoProducto.save(producto);
		daoUsuario.save(userSes);

		if(producto.getCantidad() <= 0) {
			daoProducto.delete(id);
		}
	}
	
	public ModelAndView verificarUsuario(Usuario usuario, ModelAndView modelAndView, HttpSession session) {
		if (daoUsuario.exists(usuario.getNombreDeUsuario())) {

			Usuario usuario1 = daoUsuario.findOne(usuario.getNombreDeUsuario());

			if (usuario.getContraseña().equals(usuario1.getContraseña())) {
				session.setAttribute("usuario", usuario1);

				modelAndView = this.menuPrincipalGet(session);

				modelAndView.addObject("usuario", usuario1);

			} else {

				modelAndView.setViewName("errorInicioSesion");
			}

		}else{
			modelAndView.setViewName("errorInicioSesion");
		}
		
		return modelAndView;
	}
	
	public ModelAndView verProductos(String nombreProducto, ModelAndView modelAndView, HttpSession session) {
			Usuario user = (Usuario) session.getAttribute("usuario");
			Iterable<Producto> listaProductos ;
			if(null != user) {
				if (nombreProducto == null || nombreProducto == "") {
					listaProductos = daoProducto.findAllForUser(user);
				} else {
					listaProductos = daoProducto.findByNombreProductoLike("%"+nombreProducto+"%");
					((ArrayList<Producto>) listaProductos).addAll(daoProducto.findByTagsLike("%"+nombreProducto+"%"));
					listaProductos = ((ArrayList<Producto>) listaProductos).stream().distinct().collect(Collectors.<Producto>toList());
				}
				modelAndView.addObject("listaDeProductos", listaProductos);
				//modelAndView.addObject("listaDeProductos", daoProducto.findAll());
	
				modelAndView.setViewName("verProductos");
			}
			else {
				modelAndView.setViewName("inicio");
			}
			
			return modelAndView;
		}
	
	public ModelAndView verTotalidadDeProductos(Usuario usuario, ModelAndView modelAndView, Iterable<Producto> listaProductos) {
		if(null != usuario) {
			if (usuario.getEsAdministrador()) {
				modelAndView.setViewName("verTodosLosProductos");
				modelAndView.addObject("listaDeProductos", listaProductos);
			}
			else {
				modelAndView.setViewName("menuPrincipal");
			}
		}
		else {
			modelAndView.setViewName("inicio");
		}
		
		return modelAndView;
	
	}
	
	
	public ModelAndView eliminarUsuario(Usuario usuario, ModelAndView modelAndView, String id) {
		
		if(null != usuario) {
			if(usuario.getEsAdministrador()) {
				daoUsuario.delete(id);
	
				modelAndView.addObject("listaDeUsuarios", daoUsuario.findAll());
				modelAndView.setViewName("verUsuarios");
	
			}
			else {
				modelAndView.setViewName("menuPrincipal");
			}
		}
		else {
			modelAndView.setViewName("inicio");
		}
		
		return modelAndView;
	}
	
	public ModelAndView verTotalidadDeUsuarios(ModelAndView modelAndView, Usuario usuario) {
		if(null != usuario) {
			if(usuario.getEsAdministrador()) {
	
				Iterable<Usuario> listaDeUsuarios = daoUsuario.findAll();
	
				modelAndView.addObject("listaDeUsuarios", listaDeUsuarios);
				modelAndView.setViewName("verUsuarios");
			}
			else {
				modelAndView.setViewName("menuPrincipal");
			}
		}
		else {
			modelAndView.setViewName("inicio");
		}
		
		return modelAndView;
	}
}

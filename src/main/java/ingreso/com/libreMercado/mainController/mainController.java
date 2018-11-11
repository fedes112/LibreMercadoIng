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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

		modelAndView.setViewName("index");

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

//	@RequestMapping(value= "verProductos",
//			method = RequestMethod.GET)
//	public ModelAndView mostrarProductos(){
//
//		ModelAndView modelAndView = new ModelAndView();
//
//		modelAndView.addObject("listaDeProductos", daoProducto.findAll());
//
//		modelAndView.setViewName("verProductos");
//
//		return modelAndView;
//	}

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


	@RequestMapping(value= "verProductos",
			method = RequestMethod.GET)
	public ModelAndView mostrarProductosPorBusqueda(@RequestParam(value = "nombreProducto", required = false) String nombreProducto){

		ModelAndView modelAndView = new ModelAndView();

		Iterable<Producto> listaProductos ;
		if(nombreProducto == null){
			listaProductos  = daoProducto.findAll();
		}else{
			listaProductos = daoProducto.findByNombreProductoLike(nombreProducto);
		}
		modelAndView.addObject("listaDeProductos", listaProductos);
//		modelAndView.addObject("listaDeProductos", daoProducto.findAll());

		modelAndView.addObject("ordenTrabajos", listaProductos);




		modelAndView.setViewName("verProductos");


		return modelAndView;
	}




}

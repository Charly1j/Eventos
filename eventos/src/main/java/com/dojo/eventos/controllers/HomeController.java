package com.dojo.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dojo.eventos.models.EventosModel;
import com.dojo.eventos.models.LoginUser;
import com.dojo.eventos.models.Provincias;
import com.dojo.eventos.models.User;
import com.dojo.eventos.services.EventoService;
import com.dojo.eventos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	private final UserService userService;
	private final EventoService eventoService;

	public HomeController(UserService userService, EventoService eService) {
		this.userService = userService;
		this.eventoService = eService;
		
	}

	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("registro", new User());
		modelo.addAttribute("login", new LoginUser());
		modelo.addAttribute("provincias", Provincias.provincias);
		System.out.println(" LLEGO ACA?");
		return "index.jsp";
	}

	@PostMapping("/register")
	public String registro(@Valid @ModelAttribute("registro") User nuevoUsuario, BindingResult resultado, Model modelo,
			HttpSession sesion) {

		if (resultado.hasErrors()) {
			modelo.addAttribute("login", new LoginUser());
			modelo.addAttribute("provincias", Provincias.provincias);
			return "index.jsp";
		}
		User usuarioRegistrar = userService.registrarUsuario(nuevoUsuario, resultado);

		if (usuarioRegistrar != null) {
			modelo.addAttribute("login", new LoginUser());
			modelo.addAttribute("registro", new User());
			modelo.addAttribute("provincias", Provincias.provincias);
			modelo.addAttribute("gracias", "Gracias por registrarte");

			return "index.jsp";
		} else {
			modelo.addAttribute("login", new LoginUser());
			return "index.jsp";
		}

	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LoginUser loginuser, BindingResult resultado, Model modelo,
			HttpSession sesion) {

		if (resultado.hasErrors()) {
			modelo.addAttribute("registro", new User());
			return "index.jsp";
		}
//		boolean cumple_login = userService.autenticacionUser(loginuser.getEmail(),
//				loginuser.getPassword(),
//				resultado);
		if (userService.autenticacionUser(loginuser.getEmail(), loginuser.getPassword(), resultado)) {
			User usuarioLog = userService.encontrarPorEmail(loginuser.getEmail());
			sesion.setAttribute("userID", usuarioLog.getId());
			return "redirect:/dashboard";
		}else {
			modelo.addAttribute("registro", new User());
			return "index.jsp";
		}
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpSession sesion, Model modelo, 
			@ModelAttribute("nuevoevento") EventosModel nuevoevento) {
		Long userId = (Long) sesion.getAttribute("userID");
		if(userId == null) {
			return "redirect:/";
		}
		User usuario = userService.encontrarPorId(userId);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("provincias", Provincias.provincias);
		
		return "dashboard.jsp";
	}
	
	@PostMapping("/crearevento")
	public String crearEvento(@Valid @ModelAttribute("nuevoevento")EventosModel nuevoevento, 
			BindingResult resultado, Model modelo) {
		if(resultado.hasErrors()) {
			modelo.addAttribute("provincias", Provincias.provincias);
			return "dashboard.jsp";
		}	
		eventoService.crearEvento(nuevoevento);
		return "redirect:/dashboard";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
		
	}

}

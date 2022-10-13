package org.tindertec.controller;

import org.tindertec.model.Chat;
import org.tindertec.model.Match;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.tindertec.service.ChatService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/buscarAmistad")
public class BuscarAmistadController {


	@Autowired	
	private ChatService serviceChat;
	
	
	/**
	 * @author Richard
	 */
	@GetMapping("/Chat")
	@ResponseBody
	public ResponseEntity<List<Match>> CargarMatchsEnChat(
			@RequestParam(name = "idUsuario", required = true) int idUser
			) {
		return ResponseEntity.ok(serviceChat.CargarMatchsEnChat(idUser));
	}
	@PostMapping("/CargarChat")
	@ResponseBody
	public ResponseEntity<List<Chat>> ListarChat(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "CodUsuarioSeleccionado", required = true) int CodUsuarioSeleccionado
			) {
		return ResponseEntity.ok(serviceChat.ListarChat(CodUsuInSession,CodUsuarioSeleccionado));
	}	
	
	@PostMapping("/EnviarMensaje")
	@ResponseBody
	public ResponseEntity<String> sendMensaje(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "msj_enviar", required = true) String msj_enviar,
			@RequestParam(name = "cod_usu_enviarmsj", required = true) int cod_usu_enviarmsj
			) 
	{
		return ResponseEntity.ok(serviceChat.sendMensaje(CodUsuInSession, cod_usu_enviarmsj, msj_enviar));
	}
	@PostMapping("/CancelarMatch")
	@ResponseBody
	public ResponseEntity<String> CancelarMatch(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "cod_usu_menu", required = true) int cod_usu_menu
			) 
	{
		return ResponseEntity.ok(serviceChat.CancelarMatch(CodUsuInSession, cod_usu_menu));
	}
	/**
	 * @author Jorge
	 */

/*	

	@GetMapping("/Inicio")
	public String cargarInicio(Model model) throws ParseException {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		// enviarle el usuario que inicio sesion
		int codigoValidacion = -1;
		Usuario u = repoUsua.listaBuscarAmistad(CodUsuInSession);

		if (u == null) {

			model.addAttribute("codigoValidacion", codigoValidacion);
		} else {
			codigoValidacion = 1;
			model.addAttribute("codigoValidacion", codigoValidacion);
			model.addAttribute("nombreYedadBusAmi", u.getNombres() + " ," + obtenerEdad(u.getFecha_naci()));
			model.addAttribute("fotoBusAmi", u.getFoto1());
			model.addAttribute("codigoBusAmi", u.getCod_usu());
			model.addAttribute("sedeBusAmi", u.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi", u.getCarrera().getDes_carrera());
		}
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);

		return "BuscarAmistad/BuscarAmistad";
	}

	@PostMapping("BuscarAmistad/Like")
	public String like(@ModelAttribute Usuario usu, Model model) throws ParseException {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		
		int codigoValidacion = -1;
		
		String mensaje ="";
		 mensaje = repoLike.USP_INSERTAR_LIKE(CodUsuInSession, usu.getCod_usu());

		if (mensaje != null) {
			model.addAttribute("mensajeBuscarAmistad", 1);
			codigoValidacion = 1;
			Usuario user = new Usuario();
			user=repoUsua.getOne(usu.getCod_usu());

			model.addAttribute("codigoValidacion", codigoValidacion);
			model.addAttribute("nombreYedadBusAmi", user.getNombres() + " ," + obtenerEdad(user.getFecha_naci()));
			model.addAttribute("fotoBusAmi", user.getFoto1());
			model.addAttribute("codigoBusAmi", user.getCod_usu());
			model.addAttribute("sedeBusAmi",user.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi", user.getCarrera().getDes_carrera());
			
		}
		else {
			Usuario u = repoUsua.listaBuscarAmistad(CodUsuInSession);
			if (u == null) {

				model.addAttribute("codigoValidacion", codigoValidacion);
			} else {
				codigoValidacion = 1;
				model.addAttribute("codigoValidacion", codigoValidacion);
				model.addAttribute("nombreYedadBusAmi", u.getNombres() + " ," + obtenerEdad(u.getFecha_naci()));
				model.addAttribute("fotoBusAmi", u.getFoto1());
				model.addAttribute("codigoBusAmi", u.getCod_usu());
				model.addAttribute("sedeBusAmi", u.getSede().getDes_sede());
				model.addAttribute("carreraBusAmi", u.getCarrera().getDes_carrera());

			}
		}

		// enviarle el usuario que inicio sesion

		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "BuscarAmistad/BuscarAmistad";
	}

	@PostMapping("/BuscarAmistad/disLike")
	public String dislike(@ModelAttribute Usuario usu, Model model) throws ParseException {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
// hacer con @RequestParam , recuperar el parametro enviado por el formulario post
		int codigoValidacion = -1;
		repoDislike.USP_INSERTAR_DISLIKE(CodUsuInSession, usu.getCod_usu());

		// CARD
		Usuario u = repoUsua.listaBuscarAmistad(CodUsuInSession);
		if (u == null) {

			model.addAttribute("codigoValidacion", codigoValidacion);
		} else {
			codigoValidacion = 1;
			model.addAttribute("codigoValidacion", codigoValidacion);
			model.addAttribute("nombreYedadBusAmi", u.getNombres() + " ," + obtenerEdad(u.getFecha_naci()));
			model.addAttribute("fotoBusAmi", u.getFoto1());
			model.addAttribute("codigoBusAmi", u.getCod_usu());
			model.addAttribute("sedeBusAmi", u.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi", u.getCarrera().getDes_carrera());
		}

		// enviarle el usuario que inicio sesion

		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "BuscarAmistad/BuscarAmistad";
	}
*/
}

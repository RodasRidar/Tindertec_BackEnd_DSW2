package org.tindertec.controller;

import org.tindertec.model.Chat;
import org.tindertec.model.Match;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.tindertec.service.ChatService;
import org.tindertec.service.DisLikesService;
import org.tindertec.service.MeGustasService;
import org.tindertec.service.UsuarioService;

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
	
	@Autowired
	private UsuarioService serviceUsu;
	
	@Autowired
	private DisLikesService serviceDisLike;
	
	@Autowired
	private MeGustasService serviceMeGustas;
	
	
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
		
	@GetMapping("/Inicio")
	@ResponseBody
	public ResponseEntity<Usuario> cargarInicio(
			@RequestParam(name = "idUser", required = true) int userId
			) {
		return ResponseEntity.ok(serviceUsu.listaBuscarAmistad(userId));
		
	}
	
	@PostMapping("/Like")
	@ResponseBody
	public ResponseEntity<String> like(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "CodUsuarioSeleccionado",required = true) int CodUsuarioSeleccionado
			){
		
		return ResponseEntity.ok(serviceMeGustas.like(CodUsuInSession, CodUsuarioSeleccionado));
		
	}
	
	@PostMapping("/DisLike")
	@ResponseBody
	public ResponseEntity<String> dislike(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "CodUsuarioSeleccionado",required = true) int CodUsuarioSeleccionado
			){
		return ResponseEntity.ok(serviceDisLike.disLike(CodUsuInSession, CodUsuarioSeleccionado));
		
		
	}

}

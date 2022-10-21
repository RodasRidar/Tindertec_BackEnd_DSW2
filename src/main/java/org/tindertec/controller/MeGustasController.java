package org.tindertec.controller;

import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tindertec.model.*;

import java.util.List;


///


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.tindertec.repository.IUsuarioRepository;
import org.tindertec.service.DisLikesService;
import org.tindertec.service.MeGustasService;
import org.tindertec.service.UsuarioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/megustas")
public class MeGustasController {

	/*
	@Autowired
	IUsuarioRepository usuRepo;
	@Autowired
	ILikesRepository likesRepo;
	@Autowired
	IDislikesRepository disLikesRepo;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
	// private IUsuarioRepository repoUsua;
	// @GetMapping
	// @PostMapping

	@GetMapping("/MeGustas")
	public String cargarMegustas(Model model) {

		// enviarle el usuario que inicio sesion
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;

		List<Usuario> lstusu = usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession);

		if (lstusu.isEmpty()) {
			model.addAttribute("msjEliminarLike", "!No has dado ningun ❤, ve busca amistades!");
		} else {
			
			model.addAttribute("listaUsuarios", lstusu);
		}
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		model.addAttribute("listaUsuarios", lstusu);
		return "MeGustas/MeGustas";
	}

	@PostMapping("/MeGustas/Eliminar")
	public String ProcesarMegustas(@ModelAttribute Usuario usuario, Model model) {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		System.out.println(usuario);
		// enviarle el usuario que inicio sesion

		disLikesRepo.USP_ELIMINAR_LIKE(CodUsuInSession, usuario.getCod_usu());
		// model.addAttribute("mensajeSucess", "Eliminado");

		if (usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession).isEmpty()) {

			model.addAttribute("msjEliminarLike", " 📌 !Ya no hay mas me gustas que ver!");
		} else {
			// model.addAttribute("msjEliminarLike","hay usuarios");
			model.addAttribute("listaUsuarios", usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession));
		}

		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);

		return "MeGustas/MeGustas";
	}
	*/
	/*	Autor: Hansel	*/
	
	@Autowired
	private DisLikesService serviceDisLike;
	
	@Autowired
	private MeGustasService serviceMegusta;
	
	
	@GetMapping("/MeGustas")
	public ResponseEntity<List<Usuario>> cargarLikes(
			@RequestParam(name = "idUser", required = true) int userId
			) {
		return ResponseEntity.ok(serviceMegusta.listarLikesXUser(userId));
		
	}
	
	@PostMapping("/EliminarLike")
	@ResponseBody
	public ResponseEntity<String> dislike(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "CodUsuarioSeleccionado",required = true) int CodUsuarioSeleccionado
			){
		return ResponseEntity.ok(serviceDisLike.disLike(CodUsuInSession, CodUsuarioSeleccionado));
		
		
	}
	
	
}

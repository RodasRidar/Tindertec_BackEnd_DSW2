package org.tindertec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tindertec.model.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.tindertec.service.DisLikesService;
import org.tindertec.service.MeGustasService;

@RestController
@RequestMapping("/rest/MeGustas")
public class MeGustasController {

	/**
	 * @author Hansel
	 */
	
	@Autowired
	private DisLikesService serviceDisLike;
	
	@Autowired
	private MeGustasService serviceMegusta;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> cargarLikes(
			@RequestParam(name = "idUser", required = true) int userId
			) {
		return ResponseEntity.ok(serviceMegusta.listarLikesXUser(userId));
		
	}
	
	@PostMapping("/Eliminar")
	@ResponseBody
	public ResponseEntity<String> dislike(
			@RequestParam(name = "CodUsuInSession", required = true) int CodUsuInSession,
			@RequestParam(name = "CodUsuarioSeleccionado",required = true) int CodUsuarioSeleccionado
			){
		return ResponseEntity.ok(serviceDisLike.EliminarLike(CodUsuInSession, CodUsuarioSeleccionado));
		
		
	}
	
	
}

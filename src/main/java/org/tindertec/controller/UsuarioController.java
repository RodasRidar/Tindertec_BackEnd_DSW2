package org.tindertec.controller;

import org.tindertec.model.Carreras;
import org.tindertec.model.GeneroUsuario;
import org.tindertec.model.InteresGenero;
import org.tindertec.model.Match;
import org.tindertec.model.Sedes;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.tindertec.security.service.UsuarioServiceSecurity;
import org.tindertec.service.ChatService;
import org.tindertec.service.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioController {
	
	@Autowired	
	private UsuarioService serviceUsuario;
	
	@Autowired	
	private UsuarioServiceSecurity serviceUsuarioSec;
	
	@GetMapping("/obtener")
	@ResponseBody
	public ResponseEntity<Optional<Usuario>> BuscarUsuario(
			@RequestParam(name = "idUsuario", required = true) int idUser
			) {
		return ResponseEntity.ok(serviceUsuario.BuscarUsuario(idUser));
	}
	
	@GetMapping("/obtenerEmail")
	@ResponseBody
	public ResponseEntity<Usuario> BuscarUsuarioEmail(@RequestParam(name = "email", required = true) String email) {
		
		Usuario usu = new Usuario();
		
		if(serviceUsuarioSec.obtenerUsuarioPorEmail(email) != null) {
			usu = (Usuario) serviceUsuarioSec.obtenerUsuarioPorEmail(email);
		}
		return ResponseEntity.ok(usu);
	}
	
	@GetMapping("/listaCarreras")
	@ResponseBody
	public List<Carreras> listaCarreras() {
		return serviceUsuario.listadoCarreras();
	}
	
	@GetMapping("/listaSedes")
	@ResponseBody
	public List<Sedes> listaSedes() {
		return serviceUsuario.listadoSedes();
	}
	
	@GetMapping("/listaInteres")
	@ResponseBody
	public List<InteresGenero> listaIntereses() {
		return serviceUsuario.listadoIntereses();
	}
	
	@GetMapping("/listaGeneros")
	@ResponseBody
	public List<GeneroUsuario> listaPais() {
		return serviceUsuario.listadoGeneros();
	}
	
	/**
	 * author Eduardo
	 */
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Usuario objUsuario, Errors errors) {
		
		//Creamos un Map para enviar los valores de salida
		HashMap<String, Object> salida = new HashMap<>();
		
		//Creamos una lista para poder ingresar dentro de ella un mensaje o un listado de mensajes
		List<String> lstMensajes = new ArrayList<String>();
		salida.put("errores", lstMensajes);
		
		//Obtenemos los errores de la validacion y los guardamos en una lista 
		List<ObjectError> lstErrors =  errors.getAllErrors();
		
		//Recorremoss la lista de errores y mandamos cada error a nuestro Map "salida"
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}
		
		//Si la lista de mensajes tiene contenido, retornamos ese contenido (errores)
		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		//Si es que está vacía la lista de errores (es decir, se cumplió la validacion), seguimos...
		
		try {

			serviceUsuario.registrarUsuario(objUsuario);
			
			lstMensajes.add("¡Bienvenido a Tindertec! " + objUsuario.getNombres());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			lstMensajes.add("Hubo un error en el registro de tu Usuario");
		}
		
		return ResponseEntity.ok(salida);
		
	}
	/**
	 * @author Pierina 
	 */
	@PostMapping("/editar")
	@ResponseBody
	public ResponseEntity<?> actualizarPerfil(@RequestBody Usuario u){
			return ResponseEntity.ok(serviceUsuario.editaPerfil(u));
	}
	
	@PostMapping("/agregarFoto")
	@ResponseBody
	public ResponseEntity<?> agregarFoto
			(@RequestParam(name="CodUsuInSession", required=true) int CodUsuInSession,
			 @RequestParam(name="posicion", required=true) int posicion,
			 @RequestParam(name="url_foto", required=true) String url){
		try {
			serviceUsuario.agregaFoto(CodUsuInSession, posicion, url);
			return ResponseEntity.ok("Agregaste una foto!");
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.badRequest();
		}
		return ResponseEntity.ok("Método agregar foto terminó");
	}
	@PostMapping("/eliminar")
	@ResponseBody
	public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario u){
		return ResponseEntity.ok(serviceUsuario.eliminarUsuario(u.getCod_usu()));
	}
}

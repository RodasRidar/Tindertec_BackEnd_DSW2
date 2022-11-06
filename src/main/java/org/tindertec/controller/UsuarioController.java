package org.tindertec.controller;

import org.tindertec.model.Carreras;
import org.tindertec.model.GeneroUsuario;
import org.tindertec.model.InteresGenero;
import org.tindertec.model.Sedes;
import org.tindertec.model.Usuario;
import org.tindertec.service.UsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioController {
	
	@Autowired	
	private UsuarioService serviceUsuario;
	
	@GetMapping("/obtener")
	@ResponseBody
	public ResponseEntity<Optional<Usuario>> BuscarUsuario(
			@RequestParam(name = "idUsuario", required = true) int idUser
			) {
		return ResponseEntity.ok(serviceUsuario.BuscarUsuario(idUser));
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
	
/*


	@PostMapping("/Perfil/Guardar")
	public String guardarUsuario(Model model, @ModelAttribute Usuario usuario) {

		
		try {
			repoUsu.USP_EDITAR_PERFIL(CodUsuInSession, usuario.getNombres(), usuario.getDescripcion(),
					usuario.getCod_interes(), usuario.getCod_carrera(), usuario.getCod_sede());
	
	}

	@PostMapping("/Perfil/AgregarFoto")
	public String agregarFoto(@ModelAttribute Usuario usuario, Model model,
			@RequestParam(name = "url_foto", required = true) String url_foto) {


		try {

			repoUsu.USP_USUARIO_INSERTAR_FOTO(CodUsuInSession, fotoPosicion, url_foto);
			model.addAttribute("msjConfirmacionAddFoto", "¡Foto agregada exitosamente!");

			
	}
	
	@PostMapping("/Perfil/eliminar")
	public String eliminarUsuario(@ModelAttribute Usuario usuario, Model model) {
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		try {
			repoUsu.USP_USUARIO_ELIMINAR(CodUsuInSession);
			model.addAttribute("msjConfirmation", "Usuario eliminado Correctamente");
			return "Login/Login";
		} catch (Exception e) {
			
			model.addAttribute("msjConfirmacionAddFoto", "Error al eliminar usuario");
			return "MantenerUsuario/MantenerUsuario";
		}
		
	}

	}*/
}
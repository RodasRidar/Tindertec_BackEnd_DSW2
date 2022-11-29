package org.tindertec.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tindertec.model.Usuario;
import org.tindertec.security.dto.JwtDto;
import org.tindertec.security.dto.LoginUsuario;
import org.tindertec.security.dto.Mensaje;
import org.tindertec.security.dto.NuevoUsuario;
import org.tindertec.security.entity.Rol;
import org.tindertec.security.enums.RolNombre;
import org.tindertec.security.jwt.JwtProvider;
import org.tindertec.security.service.RolService;
import org.tindertec.security.service.UsuarioServiceSecurity;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServiceSecurity usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    //PARA CREAR NUEVO USUARIO
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, Errors errors){
        
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
    	
			Usuario usuario =
	                new Usuario(nuevoUsuario.getNombres(),
	                		nuevoUsuario.getEmail(),
	                        passwordEncoder.encode(nuevoUsuario.getClave()),
	                        nuevoUsuario.getFoto1(),
	                        nuevoUsuario.getFecha_naci(),
	                        nuevoUsuario.getDescripcion(),
	                        nuevoUsuario.getCod_carrera(),
	                        nuevoUsuario.getCod_sede(),
	                        nuevoUsuario.getCod_interes(),
	                        nuevoUsuario.getCod_genero());
	        
	    	Set<Rol> roles = new HashSet<>();
	        
	    	roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
	        
	    	if(nuevoUsuario.getRoles().contains("admin"))
	            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
	        
	    	usuario.setRoles(roles);
	        usuarioService.save(usuario);
	        
	        lstMensajes.add("¡Bienvenido a Tindertec! " + nuevoUsuario.getNombres());
        
		}
		catch (Exception e) {
			e.printStackTrace();
			lstMensajes.add("Hubo un error en el registro de tu Usuario");
		}
		
		return ResponseEntity.ok(salida);
        
    }
    
    //PARA LOGUEARSE
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        
    	if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos vacíos o incompletos"), HttpStatus.BAD_REQUEST);
    	
    	try {
    	Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getClave()));
        
    	SecurityContextHolder.getContext().setAuthentication(authentication);
        
    	String jwt = jwtProvider.generateToken(authentication);
        
    	UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        
    	JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    	}
    	catch (Exception e) {
    		return new ResponseEntity(new Mensaje("Error de credenciales"), HttpStatus.BAD_REQUEST);
		}
    }
}

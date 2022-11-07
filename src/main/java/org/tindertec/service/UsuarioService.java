package org.tindertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.model.*;
import org.tindertec.repository.*;

@Service
public class UsuarioService {
	
	@Autowired	
	private IUsuarioRepository repoUsuario;
	
	@Autowired
	private ICarrerasRepository repoCarrera;
	
	@Autowired
	private IGeneroUsuarioRepository repoGenero;

	@Autowired
	private IInteresGeneroRepository repoInteres;
	
	@Autowired
	private ISedesRepository repoSede;
	
	/**
	 * @author Richard
	 */
	public Optional<Usuario>  BuscarUsuario(int userId) {

		Optional<Usuario> user=repoUsuario.findById(userId);
		
		if (user == null) {
			return null;
		}
		else {
			return user;
		}
	}
	
	/**
	 * @author Eduardo
	 */
	
	//Listado de Carreras
	public List<Carreras> listadoCarreras(){
		return repoCarrera.findAll();
	}
	
	//Listado de Sedes
	public List<Sedes> listadoSedes(){
		return repoSede.findAll();
	}
	
	//Listado de Interes del Usuario
	public List<InteresGenero> listadoIntereses(){
		return repoInteres.findAll();
	}
	
	//Listado de Genero
	public List<GeneroUsuario> listadoGeneros(){
		return repoGenero.findAll();
	}
	
	//REGISTRO DEL USUARIO:
	public void registrarUsuario(Usuario usuario) {
		repoUsuario.USP_USUARIO_REGISTRAR(
				usuario.getNombres(), 
				usuario.getEmail(),
				usuario.getFecha_naci(),
				usuario.getClave(),
				usuario.getCod_sede(),
				usuario.getCod_carrera(),
				usuario.getCod_genero(),
				usuario.getCod_interes(),
				usuario.getDescripcion(),
				usuario.getFoto1());
	}

	/**
	 * @author Jorge
	 */
	
	public Usuario listaBuscarAmistad(int userId){
		Usuario lista = repoUsuario.listaBuscarAmistad(userId);
		
		if(lista==null) {
			return null;
		}else {
			return lista;
		}
	}
	
	/**
	 * @author Pierina Lopez
	 */
	
	public String editaPerfil(Usuario u) {
		String msj = "";
		try {
			repoUsuario.USP_EDITAR_PERFIL(
					u.getCod_usu(),
					u.getNombres(),
					u.getDescripcion(),
					u.getCod_interes(),
					u.getCod_carrera(),
					u.getCod_sede());
			msj = "Cambios guardados exitosamente.";
		} catch (Exception e) {
			//msj = "Error al guardar cambios.";
			msj = e.getMessage();
		}
		return msj;
	}
	
	public String agregaFoto(int usu, int posicion, String url) {
		String msj = "";
		try {
			repoUsuario.USP_USUARIO_INSERTAR_FOTO(usu, posicion, url);
			msj = "Cambios guardados exitosamente.";
		} catch (Exception e) {
			//msj = "Error al guardar cambios.";
			msj = e.getMessage();
		}
		return msj;
	}
}

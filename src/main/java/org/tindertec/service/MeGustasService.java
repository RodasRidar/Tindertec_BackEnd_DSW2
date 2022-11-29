package org.tindertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.model.Match;
import org.tindertec.model.Usuario;
import org.tindertec.repository.ILikesRepository;
import org.tindertec.repository.IUsuarioRepository;

@Service
public class MeGustasService {
	
	@Autowired	
	private ILikesRepository repoMeGustas;
	
	@Autowired	
	private IUsuarioRepository repoUsuario;
	
	/**
	 * @author Jorge
	 */
	
	public String like(int CodUsuInSession,int CodUsuarioSeleccionado) {
		String mensaje="";
		mensaje = repoMeGustas.USP_INSERTAR_LIKE(CodUsuInSession, CodUsuarioSeleccionado);
		if(mensaje!=null) {
			return "MATCH";
		}
		return null;
	}
	/**
	 * @author Hansel
	 */
	
	public List<Usuario> listarLikesXUser(int userId) {
		List<Usuario> likes;
		likes = repoUsuario.USP_Listar_Usuarios_Likes(userId);
		if (likes == null) {
			return null;
		}
		else {
			return likes;
		}
	}


}

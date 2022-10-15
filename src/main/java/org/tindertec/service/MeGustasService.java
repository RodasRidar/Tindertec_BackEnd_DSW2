package org.tindertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.model.Usuario;
import org.tindertec.repository.ILikesRepository;
import org.tindertec.repository.IUsuarioRepository;

@Service
public class MeGustasService {
	
	@Autowired	
	private ILikesRepository repoMeGustas;
	
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
	


}

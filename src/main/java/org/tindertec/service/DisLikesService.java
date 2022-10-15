package org.tindertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.repository.IDislikesRepository;

@Service
public class DisLikesService {
	
	@Autowired
	private IDislikesRepository repoDisLike;
	
	/**
	 * @author Jorge
	 */
	
	public String disLike(int CodUsuInSession,int CodUsuarioSeleccionado) {
		String mensaje="";
		try {
			repoDisLike.USP_INSERTAR_DISLIKE(CodUsuInSession, CodUsuarioSeleccionado);
			mensaje="ok";
		} catch (Exception e) {
			mensaje=e.toString();
		}
		
		return mensaje;
	}

}

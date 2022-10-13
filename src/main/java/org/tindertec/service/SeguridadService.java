package org.tindertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.model.Usuario;
import org.tindertec.repository.IUsuarioRepository;

@Service
public class SeguridadService {
	
	@Autowired	
	private IUsuarioRepository repoUsua;
	
	public Usuario Login(Usuario user) {
		Usuario repo = repoUsua.findByEmailAndClave(user.getEmail(),user.getClave());
		if (repo == null) {
			return null;
		}
		else {
			
			return repo;
		}
	}

}

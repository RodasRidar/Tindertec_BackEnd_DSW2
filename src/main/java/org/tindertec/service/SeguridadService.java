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
	public String VerificarCredenciales(Usuario user) {
		
		String msj=repoUsua.usp_usuario_acceso(user.getEmail(),user.getClave());
		return msj;
		
	}
}

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

	
	public Optional<Usuario>  BuscarUsuario(int userId) {

		Optional<Usuario> user=repoUsuario.findById(userId);
		
		if (user == null) {
			return null;
		}
		else {
			return user;
		}
	}
}

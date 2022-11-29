package org.tindertec.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tindertec.model.Usuario;
import org.tindertec.repository.IUsuarioRepository;
import org.tindertec.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceSecurity {
	 @Autowired
    UsuarioRepository usuarioRepository;

	 @Autowired
	    IUsuarioRepository userRepoEmail;
	 
    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    //public boolean existsByNombreUsuario(String nombreUsuario){
      //  return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    //}

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    public Usuario obtenerUsuarioPorEmail (String email) {
    	return userRepoEmail.findByEmail(email);
    }
}

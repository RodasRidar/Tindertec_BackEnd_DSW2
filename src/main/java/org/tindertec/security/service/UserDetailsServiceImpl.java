package org.tindertec.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tindertec.model.Usuario;
import org.tindertec.security.entity.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioServiceSecurity usuarioService;

    //Convertimos la clase usuario en usuario principal
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByEmail(email).get();
        return UsuarioPrincipal.build(usuario);
    }
}
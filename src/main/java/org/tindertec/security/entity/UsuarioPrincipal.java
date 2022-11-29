package org.tindertec.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.tindertec.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//La clase Usuario es la que accede a la base de datos
	//Esta clase UsuarioPrincipal ya va relacionado a la seguridad
	private String nombres;
	private String email;
	private String clave;
	//convertimos la clase rol en authorities
	private Collection<? extends GrantedAuthority> authorities;	
	
	
	//Constrcutor
	public UsuarioPrincipal(String nombres, String email, String clave,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.nombres = nombres;
		this.email = email;
		this.clave = clave;
		this.authorities = authorities;
	}
	
	//Metodo importane: 
	public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombres(), usuario.getEmail(), usuario.getClave(), authorities);
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return clave;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
}

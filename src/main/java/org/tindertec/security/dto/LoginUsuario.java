package org.tindertec.security.dto;

import javax.validation.constraints.NotEmpty;

public class LoginUsuario {
	
	@NotEmpty(message = "Debe ingresar su correo")
	private String email;
	
	@NotEmpty(message = "Debe ingresar una contraseña")
	private String clave;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	
}

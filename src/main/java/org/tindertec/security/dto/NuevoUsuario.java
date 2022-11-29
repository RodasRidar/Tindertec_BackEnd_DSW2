package org.tindertec.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NuevoUsuario {

	@NotBlank(message = "Debe ingresar su nombre")
	private String nombres ;
	
	@NotBlank(message = "Debe ingresar su correo")
	private String email ;
	
	@NotBlank(message = "Debe ingresar una contraseña")
	private String clave ;
	
	@NotBlank
	private String foto1 ;
	
	private String foto2 ;
	private String foto3 ;
	private String foto4 ;
	private String foto5 ;
	
	@NotBlank(message = "Debe ingresar su fecha de nacimiento")
	private String fecha_naci ;
	
	@NotBlank(message = "Debe ingresar una descripción")
	private String descripcion ;
	
	@NotNull(message = "Debe seleccionar una carrera")
	private Integer cod_carrera ;
	
	@NotNull(message = "Debe seleccionar una sede")
	private Integer cod_sede ;
	
	@NotNull(message = "Debe seleccionar un interés")
	private Integer cod_interes ;
	
	@NotNull(message = "Debe seleccionar un género")
	private Integer cod_genero ;
	
	private Set<String> roles = new HashSet<>();

	/*public NuevoUsuario(@NotEmpty(message = "Debe ingresar su nombre") String nombres,
			@NotBlank(message = "Debe ingresar su correo") String email,
			@NotBlank(message = "Debe ingresar una contraseña") String clave, @NotEmpty String foto1,
			@NotBlank(message = "Debe ingresar su fecha de nacimiento") String fecha_naci,
			@NotBlank(message = "Debe ingresar una descripción") String descripcion,
			@NotNull(message = "Debe seleccionar una carrera") Integer cod_carrera,
			@NotNull(message = "Debe seleccionar una sede") Integer cod_sede,
			@NotNull(message = "Debe seleccionar un interés") Integer cod_interes,
			@NotNull(message = "Debe seleccionar un género") Integer cod_genero, Set<String> roles) {
		super();
		this.nombres = nombres;
		this.email = email;
		this.clave = clave;
		this.foto1 = foto1;
		this.fecha_naci = fecha_naci;
		this.descripcion = descripcion;
		this.cod_carrera = cod_carrera;
		this.cod_sede = cod_sede;
		this.cod_interes = cod_interes;
		this.cod_genero = cod_genero;
		this.roles = roles;
	}*/

	public String getNombres() {
		return nombres;
	}

	/*public NuevoUsuario() {
		
	}
*/
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

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

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public String getFoto4() {
		return foto4;
	}

	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}

	public String getFoto5() {
		return foto5;
	}

	public void setFoto5(String foto5) {
		this.foto5 = foto5;
	}

	public String getFecha_naci() {
		return fecha_naci;
	}

	public void setFecha_naci(String fecha_naci) {
		this.fecha_naci = fecha_naci;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCod_carrera() {
		return cod_carrera;
	}

	public void setCod_carrera(Integer cod_carrera) {
		this.cod_carrera = cod_carrera;
	}

	public Integer getCod_sede() {
		return cod_sede;
	}

	public void setCod_sede(Integer cod_sede) {
		this.cod_sede = cod_sede;
	}

	public Integer getCod_interes() {
		return cod_interes;
	}

	public void setCod_interes(Integer cod_interes) {
		this.cod_interes = cod_interes;
	}

	public Integer getCod_genero() {
		return cod_genero;
	}

	public void setCod_genero(Integer cod_genero) {
		this.cod_genero = cod_genero;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}

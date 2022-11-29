package org.tindertec.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.tindertec.security.entity.Rol;

import lombok.Data;
@Data
@Entity
@Table(name="tb_usuario")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_usu  ;
	
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
	
	@ManyToOne
	@JoinColumn(name="cod_carrera", insertable=false, updatable=false)
	private Carreras carrera; 
	
	@NotNull(message = "Debe seleccionar una sede")
	private Integer cod_sede ;
	
	@ManyToOne
	@JoinColumn(name="cod_sede", insertable=false, updatable=false)
	private Sedes sede; 
	
	@NotNull(message = "Debe seleccionar un interés")
	private Integer cod_interes ;
	
	@ManyToOne
	@JoinColumn(name="cod_interes", insertable=false, updatable=false)
	private InteresGenero interesGenero; 
	
	@NotNull(message = "Debe seleccionar un género")
	private Integer cod_genero ;
	
	@ManyToOne
	@JoinColumn(name="cod_genero", insertable=false, updatable=false)
	private GeneroUsuario genero;
	
	
	//SECURITY
	//La clase Usuario es la que accede a la base de datos
	//Conjunto de roles
	 @NotNull
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	 inverseJoinColumns = @JoinColumn(name = "rol_id"))
	 private Set<Rol> roles = new HashSet<>();
	
	
	
	/*
	public Usuario(int cod_usu, String nombres, String email, String clave, String foto1, String fecha_naci,
			String descripcion, int cod_carrera, int cod_sede, int cod_interes, int cod_genero) {
		this.cod_usu = cod_usu;
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
	}*/


	public Usuario() {

	}


	public Usuario(String nombres, String email, String clave, String foto1, String fecha_naci, String descripcion,
			int cod_carrera, int cod_sede, int cod_interes, int cod_genero) {
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
	}

	//Security
	/*public Usuario(@NotBlank(message = "Debe ingresar su nombre") String nombres,
			@NotBlank(message = "Debe ingresar su correo") String email,
			@NotBlank(message = "Debe ingresar una contraseña") String clave, @NotBlank String foto1,
			@NotBlank(message = "Debe ingresar su fecha de nacimiento") String fecha_naci,
			@NotBlank(message = "Debe ingresar una descripción") String descripcion,
			@NotNull(message = "Debe seleccionar una carrera") Integer cod_carrera,
			@NotNull(message = "Debe seleccionar una sede") Integer cod_sede,
			@NotNull(message = "Debe seleccionar un interés") Integer cod_interes,
			@NotNull(message = "Debe seleccionar un género") Integer cod_genero, @NotNull Set<Rol> roles) {
		
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
	} */

	
}

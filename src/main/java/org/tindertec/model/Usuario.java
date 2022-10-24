package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
@Entity(name="tb_usuario")
@Table(name="tb_usuario")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_usu  ;
	
	@NotEmpty(message = "Debe ingresar su nombre")
	private String nombres ;
	
	@NotEmpty(message = "Debe ingresar su correo")
	private String email ;
	
	@NotEmpty(message = "Debe ingresar una contraseña")
	private String clave ;
	
	@NotEmpty
	private String foto1 ;
	
	private String foto2 ;
	private String foto3 ;
	private String foto4 ;
	private String foto5 ;
	
	@NotEmpty(message = "Debe ingresar su fecha de nacimiento")
	private String fecha_naci ;
	
	@NotEmpty(message = "Debe ingresar una descripción")
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
	}


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

	
}

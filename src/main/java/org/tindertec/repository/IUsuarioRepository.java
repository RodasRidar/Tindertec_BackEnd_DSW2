package org.tindertec.repository;
import org.tindertec.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository <Usuario, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	@Query (value = "{call USP_Listar_Usuarios_Likes(:sp_cod_usu)}",nativeQuery = true)
	List<Usuario> USP_Listar_Usuarios_Likes(@Param("sp_cod_usu")int cod_usu);
	
	@Query(value="{CALL USP_USUARIO_LISTAR(:spa_cod_usu)}",nativeQuery = true)
	Usuario listaBuscarAmistad(@Param("spa_cod_usu")int cod_usu);

	Usuario findByEmailAndClave(String email, String clave);
	
	Usuario findByEmail(String email);
	
	@Procedure(procedureName = "usp_usuario_acceso")
	String usp_usuario_acceso(
			@Param("correo")String correo,
			@Param("clave")String clave);
	
	@Procedure(procedureName = "USP_USUARIO_REGISTRAR")
	void USP_USUARIO_REGISTRAR(
			@Param("nombre")String nombres,
			@Param("correo")String email,
			@Param("fecnac")String fecha_naci,
			@Param("clave")String clave,
			@Param("idsede")Integer idsede,
			@Param("idcarrera")Integer idcarrera,
			@Param("idgenero")Integer idgenero,
			@Param("idinteres")Integer idinteres,
			@Param("descripcion")String descripcion,
			@Param("f1")String f1
			);
	@Procedure(procedureName = "USP_EDITAR_PERFIL")
	void USP_EDITAR_PERFIL(
			@Param("cod_usu_sp")int cod_usu_sp,
			@Param("nombres_sp")String nombres_sp,
			@Param("descripcion_sp")String descripcion_sp,
			@Param("cod_interes_sp")int cod_interes_sp,
			@Param("cod_carrera_sp")int cod_carrera_sp,
			@Param("cod_sede_sp")int cod_sede_sp
			);
	
	
	
	@Procedure(procedureName = "USP_USUARIO_INSERTAR_FOTO")
	void USP_USUARIO_INSERTAR_FOTO(
			@Param("codigo_usuario")int codigo_usuario,
			@Param("posicion")int posicion,
			@Param("url_foto")String url_foto);
	
	
	@Procedure(procedureName = "USP_USUARIO_ELIMINAR")
	void USP_USUARIO_ELIMINAR(
			@Param("codigo_usuario")int codigo_usuario);
	
	
	//SECURITY
	//@Query("Select x from Usuario x where x.email = :#{#usu.email} and x.clave = :#{#usu.clave}")
	//public abstract Usuario login(@Param(value = "usu") Usuario bean);
	
	//@Query("Select p from Opcion p, RolHasOpcion pr, Rol r, UsuarioHasRol u where  p.idOpcion = pr.opcion.idOpcion and pr.rol.idRol = r.idRol and r.idRol = u.rol.idRol and u.usuario.cod_usu = :var_idUsuario")
	//public abstract List<Opcion> traerEnlacesDeUsuario(@Param("var_idUsuario") int idUsuario);

	//@Query("Select r from Rol r, UsuarioHasRol u where r.idRol = u.rol.idRol and u.usuario.cod_usu = :var_idUsuario")
//	public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario")int idUsuario);
	
	//public abstract Usuario findByEmail(String login);
	
}

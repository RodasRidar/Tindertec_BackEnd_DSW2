package org.tindertec.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tindertec.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String nombreUsuario);
    boolean existsByEmail(String email);
    //boolean existsByEmail(String nombreUsuario);
}
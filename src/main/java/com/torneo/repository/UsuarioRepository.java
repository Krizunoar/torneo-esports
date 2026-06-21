package com.torneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByUsername(String username);
}
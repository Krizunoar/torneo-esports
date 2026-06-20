package com.torneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
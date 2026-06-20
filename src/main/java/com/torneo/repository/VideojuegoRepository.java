package com.torneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Videojuego;

public interface VideojuegoRepository
        extends JpaRepository<Videojuego, Integer>{

}
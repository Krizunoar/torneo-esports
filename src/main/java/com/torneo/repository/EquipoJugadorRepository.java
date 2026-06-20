package com.torneo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.EquipoJugador;

public interface EquipoJugadorRepository extends JpaRepository<EquipoJugador, Integer> {
    List<EquipoJugador> findByEquipoIdEquipo(Integer idEquipo);
}
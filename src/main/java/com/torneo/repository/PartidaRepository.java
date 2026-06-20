package com.torneo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
    List<Partida> findByTorneoIdTorneo(Integer idTorneo);
    List<Partida> findByEquipo1IdEquipoOrEquipo2IdEquipo(Integer idEquipo1, Integer idEquipo2);
    
}
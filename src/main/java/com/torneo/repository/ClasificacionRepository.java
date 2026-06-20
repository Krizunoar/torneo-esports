package com.torneo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Clasificacion;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer> {

    List<Clasificacion> findByTorneoIdTorneoOrderByPuntosDesc(Integer idTorneo);

    Optional<Clasificacion> findByTorneoIdTorneoAndEquipoIdEquipo(Integer idTorneo, Integer idEquipo);
}
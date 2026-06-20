package com.torneo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByTorneoIdTorneo(Integer idTorneo);
}
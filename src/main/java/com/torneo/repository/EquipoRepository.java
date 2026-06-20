package com.torneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
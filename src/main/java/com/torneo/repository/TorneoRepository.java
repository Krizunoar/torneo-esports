package com.torneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torneo.entity.Torneo;

public interface TorneoRepository
        extends JpaRepository<Torneo, Integer> {

}
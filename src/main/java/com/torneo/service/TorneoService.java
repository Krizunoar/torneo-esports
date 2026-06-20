package com.torneo.service;

import java.util.List;

import com.torneo.entity.Torneo;

public interface TorneoService {
    List<Torneo> listarTodos();
    Torneo guardar(Torneo torneo);
    Torneo buscarPorId(Integer id);
}

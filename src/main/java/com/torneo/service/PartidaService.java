package com.torneo.service;

import java.util.List;
import com.torneo.entity.Partida;

public interface PartidaService {
    List<Partida> listarPorTorneo(Integer idTorneo);
    Partida buscarPorId(Integer id);
    Partida guardar(Partida partida);
    Partida registrarResultado(Integer idPartida, Integer scoreEquipo1, Integer scoreEquipo2);
    List<Partida> listarPorEquipo(Integer idEquipo);
}
package com.torneo.service;

import java.util.List;
import com.torneo.entity.EquipoJugador;

public interface EquipoJugadorService {
    List<EquipoJugador> listarPorEquipo(Integer idEquipo);
    EquipoJugador guardar(EquipoJugador equipoJugador);
    void eliminar(Integer id);
}
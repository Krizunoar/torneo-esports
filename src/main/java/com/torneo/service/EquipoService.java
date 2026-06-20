package com.torneo.service;

import java.util.List;
import com.torneo.entity.Equipo;

public interface EquipoService {
    List<Equipo> listarTodos();
    Equipo guardar(Equipo equipo);
    Equipo buscarPorId(Integer id);
    void eliminar(Integer id);
}
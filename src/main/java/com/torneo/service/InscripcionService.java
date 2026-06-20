package com.torneo.service;

import java.util.List;
import com.torneo.entity.Inscripcion;

public interface InscripcionService {
    List<Inscripcion> listarTodos();
    List<Inscripcion> listarPorTorneo(Integer idTorneo);
    Inscripcion guardar(Inscripcion inscripcion);
    void eliminar(Integer id);
}
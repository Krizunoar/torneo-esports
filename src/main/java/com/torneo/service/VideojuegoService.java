package com.torneo.service;

import java.util.List;

import com.torneo.entity.Videojuego;

public interface VideojuegoService {

    List<Videojuego> listarTodos();

    Videojuego guardar(Videojuego videojuego);
    
    Videojuego buscarPorId(Integer id);

    void eliminar(Integer id);

}
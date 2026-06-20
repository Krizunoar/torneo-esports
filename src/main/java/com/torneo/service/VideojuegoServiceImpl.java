package com.torneo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torneo.entity.Videojuego;
import com.torneo.repository.VideojuegoRepository;

@Service
public class VideojuegoServiceImpl
        implements VideojuegoService {

    @Autowired
    private VideojuegoRepository repository;

    @Override
    public List<Videojuego> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Videojuego guardar(Videojuego videojuego) {
        return repository.save(videojuego);
    }

	@Override
	public Videojuego buscarPorId(Integer id) {
	    return repository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
	    repository.deleteById(id);
		
	}
}
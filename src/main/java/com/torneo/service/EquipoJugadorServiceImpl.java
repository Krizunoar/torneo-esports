package com.torneo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.torneo.entity.EquipoJugador;
import com.torneo.repository.EquipoJugadorRepository;

@Service
public class EquipoJugadorServiceImpl implements EquipoJugadorService {

    @Autowired
    private EquipoJugadorRepository repository;

    @Override
    public List<EquipoJugador> listarPorEquipo(Integer idEquipo) {
        return repository.findByEquipoIdEquipo(idEquipo);
    }

    @Override
    public EquipoJugador guardar(EquipoJugador equipoJugador) {
        return repository.save(equipoJugador);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
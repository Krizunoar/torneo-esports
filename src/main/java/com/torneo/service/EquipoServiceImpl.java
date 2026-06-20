package com.torneo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.torneo.entity.Equipo;
import com.torneo.repository.EquipoRepository;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository repository;

    @Override
    public List<Equipo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Equipo guardar(Equipo equipo) {
        return repository.save(equipo);
    }

    @Override
    public Equipo buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
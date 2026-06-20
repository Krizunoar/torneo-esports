package com.torneo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torneo.entity.Torneo;
import com.torneo.repository.TorneoRepository;

@Service
public class TorneoServiceImpl implements TorneoService{

    @Autowired
    private TorneoRepository repository;

    @Override
    public List<Torneo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Torneo guardar(Torneo torneo) {
        return repository.save(torneo);
    }
    
    @Override
    public Torneo buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
}
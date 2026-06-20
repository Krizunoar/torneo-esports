package com.torneo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.torneo.entity.Inscripcion;
import com.torneo.repository.InscripcionRepository;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository repository;

    @Override
    public List<Inscripcion> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Inscripcion> listarPorTorneo(Integer idTorneo) {
        return repository.findByTorneoIdTorneo(idTorneo);
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        if (inscripcion.getFechaInscripcion() == null) {
            inscripcion.setFechaInscripcion(LocalDateTime.now());
        }
        return repository.save(inscripcion);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
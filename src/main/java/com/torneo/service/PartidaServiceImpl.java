package com.torneo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.torneo.entity.Clasificacion;
import com.torneo.entity.Equipo;
import com.torneo.entity.Partida;
import com.torneo.repository.ClasificacionRepository;
import com.torneo.repository.PartidaRepository;

@Service
public class PartidaServiceImpl implements PartidaService {

    @Autowired
    private PartidaRepository repository;

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Override
    public List<Partida> listarPorTorneo(Integer idTorneo) {
        return repository.findByTorneoIdTorneo(idTorneo);
    }

    @Override
    public Partida buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Partida guardar(Partida partida) {
        if (partida.getFechaPartida() == null) {
            partida.setFechaPartida(LocalDateTime.now());
        }
        if (partida.getEstado() == null) {
            partida.setEstado("PENDIENTE");
        }
        return repository.save(partida);
    }

    @Override
    @Transactional
    public Partida registrarResultado(Integer idPartida, Integer scoreEquipo1, Integer scoreEquipo2) {

        // 1. Obtener la partida
        Partida partida = repository.findById(idPartida)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        // 2. Guardar los scores
        partida.setScoreEquipo1(scoreEquipo1);
        partida.setScoreEquipo2(scoreEquipo2);

        // 3. Determinar el ganador (por ahora no se permite empate)
        Equipo equipoGanador;
        Equipo equipoPerdedor;

        if (scoreEquipo1 > scoreEquipo2) {
            equipoGanador = partida.getEquipo1();
            equipoPerdedor = partida.getEquipo2();
        } else if (scoreEquipo2 > scoreEquipo1) {
            equipoGanador = partida.getEquipo2();
            equipoPerdedor = partida.getEquipo1();
        } else {
            throw new RuntimeException("No se permiten empates en este formato de torneo");
        }

        partida.setEquipoGanador(equipoGanador);
        partida.setEstado("FINALIZADA");

        // 4. Actualizar clasificación del equipo GANADOR
        actualizarClasificacion(
                partida.getTorneo().getIdTorneo(),
                equipoGanador.getIdEquipo(),
                true);

        // 5. Actualizar clasificación del equipo PERDEDOR
        actualizarClasificacion(
                partida.getTorneo().getIdTorneo(),
                equipoPerdedor.getIdEquipo(),
                false);

        // 6. Guardar la partida actualizada
        return repository.save(partida);
    }

    // Método auxiliar: busca la clasificación del equipo en el torneo,
    // si no existe la crea, y suma puntos/victoria/derrota según corresponda
    private void actualizarClasificacion(Integer idTorneo, Integer idEquipo, boolean gano) {

        Optional<Clasificacion> existente =
                clasificacionRepository.findByTorneoIdTorneoAndEquipoIdEquipo(idTorneo, idEquipo);

        Clasificacion clasificacion;

        if (existente.isPresent()) {
            clasificacion = existente.get();
        } else {
            clasificacion = new Clasificacion();
            clasificacion.setPuntos(0);
            clasificacion.setVictorias(0);
            clasificacion.setDerrotas(0);
            clasificacion.setPosicion(0);
            // Se asignan torneo y equipo mínimos (por referencia, sin cargar el objeto completo)
            com.torneo.entity.Torneo t = new com.torneo.entity.Torneo();
            t.setIdTorneo(idTorneo);
            clasificacion.setTorneo(t);

            com.torneo.entity.Equipo e = new com.torneo.entity.Equipo();
            e.setIdEquipo(idEquipo);
            clasificacion.setEquipo(e);
        }

        if (gano) {
            clasificacion.setVictorias(clasificacion.getVictorias() + 1);
            clasificacion.setPuntos(clasificacion.getPuntos() + 3); // 3 puntos por victoria
        } else {
            clasificacion.setDerrotas(clasificacion.getDerrotas() + 1);
            // no se suman puntos por derrota
        }

        clasificacionRepository.save(clasificacion);
    }

	@Override
	public List<Partida> listarPorEquipo(Integer idEquipo) {
		return repository.findByEquipo1IdEquipoOrEquipo2IdEquipo(idEquipo, idEquipo);
	}
}
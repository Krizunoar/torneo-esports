package com.torneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.torneo.entity.Partida;
import com.torneo.service.EquipoService;
import com.torneo.service.InscripcionService;
import com.torneo.service.PartidaService;
import com.torneo.service.TorneoService;

@Controller
public class PartidaController {

    @Autowired
    private PartidaService service;

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private InscripcionService inscripcionService;
    
    @Autowired
    private EquipoService equipoService;

    // Ver partidas de un torneo + formulario para crear una nueva
    @GetMapping("/torneos/{id}/partidas")
    public String listar(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "torneo",
                torneoService.buscarPorId(id));

        model.addAttribute(
                "listaPartidas",
                service.listarPorTorneo(id));

        model.addAttribute(
                "listaInscripciones",
                inscripcionService.listarPorTorneo(id));

        model.addAttribute(
                "nuevaPartida",
                new Partida());

        return "partidas";
    }

    // Crear una partida nueva (sin resultado todavía, estado PENDIENTE)
    @PostMapping("/torneos/{id}/partidas/crear")
    public String crear(
            @PathVariable Integer id,
            @ModelAttribute Partida nuevaPartida) {

        nuevaPartida.setTorneo(torneoService.buscarPorId(id));

        service.guardar(nuevaPartida);

        return "redirect:/torneos/" + id + "/partidas";
    }

    // Registrar resultado de una partida (esto dispara la transacción)
    @PostMapping("/partidas/{id}/resultado")
    public String registrarResultado(
            @PathVariable Integer id,
            @RequestParam Integer scoreEquipo1,
            @RequestParam Integer scoreEquipo2) {

        Partida partida = service.buscarPorId(id);
        Integer idTorneo = partida.getTorneo().getIdTorneo();

        service.registrarResultado(id, scoreEquipo1, scoreEquipo2);

        return "redirect:/torneos/" + idTorneo + "/partidas";
    }
    
    @GetMapping("/equipos/{id}/historial")
    public String historialEquipo(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "equipo",
                equipoService.buscarPorId(id));

        model.addAttribute(
                "listaPartidas",
                service.listarPorEquipo(id));

        return "historial-equipo";
    }
    
}
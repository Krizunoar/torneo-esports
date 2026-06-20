package com.torneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.torneo.entity.Inscripcion;
import com.torneo.service.EquipoService;
import com.torneo.service.InscripcionService;
import com.torneo.service.TorneoService;

@Controller
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private EquipoService equipoService;

    // Ver torneo con sus equipos inscritos + formulario para inscribir uno nuevo
    @GetMapping("/torneos/{id}/inscripciones")
    public String verInscripciones(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "torneo",
                torneoService.buscarPorId(id));

        model.addAttribute(
                "listaInscripciones",
                service.listarPorTorneo(id));

        model.addAttribute(
                "listaEquipos",
                equipoService.listarTodos());

        model.addAttribute(
                "nuevaInscripcion",
                new Inscripcion());

        return "inscripciones";
    }

    @PostMapping("/torneos/{id}/inscribir")
    public String inscribir(
            @PathVariable Integer id,
            @ModelAttribute Inscripcion nuevaInscripcion) {

        nuevaInscripcion.setTorneo(torneoService.buscarPorId(id));

        service.guardar(nuevaInscripcion);

        return "redirect:/torneos/" + id + "/inscripciones";
    }

    @GetMapping("/inscripciones/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return "redirect:/torneos";
    }
}
package com.torneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.torneo.entity.Equipo;
import com.torneo.entity.EquipoJugador;
import com.torneo.service.EquipoService;
import com.torneo.service.EquipoJugadorService;
import com.torneo.service.UsuarioService;
import com.torneo.service.VideojuegoService;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService service;

    @Autowired
    private EquipoJugadorService equipoJugadorService;

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/equipos")
    public String listar(Model model) {

        model.addAttribute(
                "listaEquipos",
                service.listarTodos());

        return "equipos";
    }

    @GetMapping("/equipos/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("equipo", new Equipo());

        model.addAttribute(
                "listaVideojuegos",
                videojuegoService.listarTodos());

        model.addAttribute(
                "listaUsuarios",
                usuarioService.listarTodos());

        return "equipos-form";
    }

    @PostMapping("/equipos/guardar")
    public String guardar(@ModelAttribute Equipo equipo) {

        service.guardar(equipo);

        return "redirect:/equipos";
    }

    // Ver el detalle de un equipo: sus jugadores
    @GetMapping("/equipos/{id}")
    public String detalle(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "equipo",
                service.buscarPorId(id));

        model.addAttribute(
                "listaJugadores",
                equipoJugadorService.listarPorEquipo(id));

        model.addAttribute(
                "listaUsuarios",
                usuarioService.listarTodos());

        model.addAttribute(
                "nuevoJugador",
                new EquipoJugador());

        return "equipos-detalle";
    }

    // Agregar un jugador al equipo
    @PostMapping("/equipos/{id}/agregar-jugador")
    public String agregarJugador(
            @PathVariable Integer id,
            @ModelAttribute EquipoJugador nuevoJugador) {

        Equipo equipo = service.buscarPorId(id);
        nuevoJugador.setEquipo(equipo);

        equipoJugadorService.guardar(nuevoJugador);

        return "redirect:/equipos/" + id;
    }

    @GetMapping("/equipos/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return "redirect:/equipos";
    }
}
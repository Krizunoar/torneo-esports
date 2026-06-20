package com.torneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.torneo.service.VideojuegoService;
import com.torneo.entity.Torneo;
import com.torneo.service.TorneoService;
import com.torneo.service.UsuarioService;

@Controller
public class TorneoController {

    @Autowired
    private TorneoService service;
    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/torneos")
    public String listar(Model model) {

        model.addAttribute(
                "listaTorneos",
                service.listarTodos());

        return "torneos";
    }

    @GetMapping("/torneos/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("torneo", new Torneo());

        model.addAttribute(
                "listaVideojuegos",
                videojuegoService.listarTodos());

        model.addAttribute(
                "listaOrganizadores",
                usuarioService.listarTodos());

        return "torneos-form";
    }

    @PostMapping("/torneos/guardar")
    public String guardar(
            @ModelAttribute Torneo torneo) {

        service.guardar(torneo);

        return "redirect:/torneos";
    }
}
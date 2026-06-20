package com.torneo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.torneo.entity.Videojuego;
import com.torneo.service.VideojuegoService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class VideojuegoController {

	@Autowired
	private VideojuegoService service;
	
    @GetMapping("/videojuegos")
    public String listar(Model model) {

    	 model.addAttribute(
    	            "listaVideojuegos",
    	            service.listarTodos()
    	        );
    	
    	return "videojuegos";

    }
    
    
    @GetMapping("/videojuegos/nuevo")
    public String nuevo(Model model) {

        model.addAttribute(
                "videojuego",
                new Videojuego());

        return "videojuegos-form";
    }

    @PostMapping("/videojuegos/guardar")
    public String guardar(
            @ModelAttribute Videojuego videojuego) {

        videojuego.setEstado(true);

        service.guardar(videojuego);

        return "redirect:/videojuegos";
    }
    
    @GetMapping("/videojuegos/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "videojuego",
                service.buscarPorId(id));

        return "videojuegos-form";
    }
    
    @GetMapping("/videojuegos/eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id) {

        service.eliminar(id);

        return "redirect:/videojuegos";
    }
    
}
package com.torneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.torneo.entity.Usuario;
import com.torneo.service.RolService;
import com.torneo.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private RolService rolService;

    @GetMapping("/usuarios")
    public String listar(Model model) {

        model.addAttribute(
                "listaUsuarios",
                service.listarTodos());

        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("usuario", new Usuario());

        model.addAttribute(
                "listaRoles",
                rolService.listarTodos());

        return "usuarios-form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {

        if (usuario.getEstado() == null) {
            usuario.setEstado(true);
        }

        service.guardar(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "usuario",
                service.buscarPorId(id));

        model.addAttribute(
                "listaRoles",
                rolService.listarTodos());

        return "usuarios-form";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return "redirect:/usuarios";
    }
}
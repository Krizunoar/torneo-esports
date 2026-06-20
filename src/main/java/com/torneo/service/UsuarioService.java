package com.torneo.service;

import java.util.List;
import com.torneo.entity.Usuario;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
}
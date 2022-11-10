package com.sistema.loginup.servicios;

import java.util.List;
import java.util.Set;

import com.sistema.loginup.modelo.Usuario;
import com.sistema.loginup.modelo.UsuarioRol;

public interface UsuarioService {
// metodo que guarda un usuario y se le pasan un conjunto de roles.
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);

    public List<Usuario> obtenerUsuarios();

    public Usuario modificarUsuario(Long usuarioID);
}

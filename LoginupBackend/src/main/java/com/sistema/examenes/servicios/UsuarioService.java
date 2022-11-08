package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import java.util.List;
import java.util.Set;

public interface UsuarioService {
// metodo que guarda un usuario y se le pasan un conjunto de roles.
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);

    public List<Usuario> obtenerUsuarios();

    public Usuario modificarUsuario(Long usuarioID);
}

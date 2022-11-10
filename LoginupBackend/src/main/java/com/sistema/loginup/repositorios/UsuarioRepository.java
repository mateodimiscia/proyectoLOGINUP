package com.sistema.loginup.repositorios;
import org.springframework.data.repository.CrudRepository;

import com.sistema.loginup.modelo.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    public Usuario findByUsername(String username);
    /*
     * sirve para buscar  un usuario por su nombre
     */

}

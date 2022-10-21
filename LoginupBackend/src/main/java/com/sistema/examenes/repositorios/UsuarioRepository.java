package com.sistema.examenes.repositorios;
import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    public Usuario findByUsername(String username);
    /*
     * sirve para buscar  un usuario por su nombre
     */

}

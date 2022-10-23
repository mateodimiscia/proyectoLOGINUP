package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//SpringSecurity
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    //permite la busqueda de un usuario por su username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//
     //se crea objeto usuario, se le pasa el username y lo busca
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        //si el usuario es nulo es porque no existe, entonces se pasa la excepcion junto con un mensaje de usuario no encontrado.
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        //en caso de encontrarlo retorna el usuario
        return usuario;
    }

}

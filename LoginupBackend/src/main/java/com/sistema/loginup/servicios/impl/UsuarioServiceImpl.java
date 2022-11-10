package com.sistema.loginup.servicios.impl;
import com.excepciones.UsuarioFoundException;
import com.sistema.loginup.modelo.Usuario;
import com.sistema.loginup.modelo.UsuarioRol;
import com.sistema.loginup.repositorios.RolRepository;
import com.sistema.loginup.repositorios.UsuarioRepository;
import com.sistema.loginup.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
//Es una clase de implementación de servicios
@Service
public class UsuarioServiceImpl implements UsuarioService {
//Se aplican inyecciones de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        //Se utiliza la excepcion creada en la carpeta excepciones
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
      
        return (List<Usuario>) usuarioRepository.findAll();

    }

    @Override
    public Usuario modificarUsuario(Long usuarioId) {
      
            if(usuarioRepository.findById(usuarioId).get().isEnabled()){
                usuarioRepository.findById(usuarioId).get().setEnabled(false);
            }else{
                usuarioRepository.findById(usuarioId).get().setEnabled(true);
            }
            //guarda el estado modificado del usuario u lo retorna
            usuarioRepository.save(usuarioRepository.findById(usuarioId).get());
        return usuarioRepository.findById(usuarioId).get();
    }
}
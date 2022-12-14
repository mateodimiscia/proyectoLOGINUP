package com.sistema.loginup.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.sistema.loginup.modelo.Rol;
import com.sistema.loginup.modelo.Usuario;
import com.sistema.loginup.modelo.UsuarioRol;
import com.sistema.loginup.servicios.UsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    // se aplica el patron de inyección de dependencias
    @Autowired
    // ya inyectado, crea la instancia del encriptado de password
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {

            usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
            // la clave se encripta. Con el requestbody los recibe y al obtener el password se encripta.
            Set<UsuarioRol> usuarioRoles = new HashSet<>();

            Rol rol = new Rol();
            rol.setRolId(2L);
            rol.setRolNombre("NORMAL");

            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(usuario);
            usuarioRol.setRol(rol);
            usuarioRoles.add(usuarioRol);
            return usuarioService.guardarUsuario(usuario, usuarioRoles);
            
        }
    /*
     * obtener datos de un usuario en particular
     * EJEMPLO: pasar por Postman metodo get con la siguiente ruta:
     * http://localhost:8080/usuarios/usuarioEnParticular
     */
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }
    // eliminar usuarios *NO UTILIZADA, sino que se va a utlizar una eliminación
    // lógica de usuarios que es inhabilitación de los mismos*
    /*
     * @DeleteMapping("/{usuarioId}")
     * public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
     * usuarioService.eliminarUsuario(usuarioId);
     * }
     */
    @GetMapping("/todos")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
        // muestra la totalidad de usuarios existentes en la base de datos
    }
    // modifica mediate el id de cada usuario el estado, puede habilitarlos o
    // deshabilitarlos.
    @PutMapping("/modificado/{usuarioId}")
    public Usuario ModificarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.modificarUsuario(usuarioId);
    }
}

package com.sistema.loginup.controladores;
import com.sistema.loginup.configuraciones.JwtUtils;
import com.sistema.loginup.modelo.JwtRequest;
import com.sistema.loginup.modelo.JwtResponse;
import com.sistema.loginup.modelo.Usuario;
import com.sistema.loginup.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
/*
 * marca la clase como un controlador donde cada método devuelve un objeto de dominio
 *  en lugar de una vista. Al anotar una clase con esta anotación, ya no necesita agregar @ResponseBody
 *  a todos los métodos RequestMapping. Significa que ya no usa resuelve vistas ni envía HTML en respuesta. 
 * Simplemente envía el objeto de dominio como una respuesta HTTP en formato JSON.
 */
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
            /*
            el metodo autenticar se le envia un username y un password.
            tanto ese username y contraseña sean correctos para generar el token
            */
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        //generar token pasando los datos del usuario.
        /*
        cuando se realiza una petición siempre se envía el token para autenticar si el usuario y contraseña son correctos
        luego se le aplican los respectivos filtros para validarlos y enviar una respuesta.
        */
        String token = this.jwtUtils.generateToken(userDetails);
        //con ese token podemos ir a cualquier ruta teniendo permisos
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
            //se pasa un username y contraseña 
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
    
//devuelve al actual usuario que acaba de iniciar sesion 
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        //se retorna el actual usuario
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}

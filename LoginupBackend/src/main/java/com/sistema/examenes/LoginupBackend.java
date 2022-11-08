package com.sistema.examenes;
import com.excepciones.UsuarioFoundException;
import com.sistema.examenes.modelo.Rol;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
 * BCriypPasswordEncoder se utiliza para cifrar las credenciales de los usuarios al visualizarlas en la base de datos.
 *  pertenece a la clase BCryptPasswordEncoder de java.lang.Object
 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html
 */
@SpringBootApplication
public class LoginupBackend implements CommandLineRunner {
	/*
	 * Se inyecta la interfaz de UsuarioService para que llame un método. 
	 * Implementando a CommandLinerRunner para sobreescribir el método run
	 */
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(LoginupBackend.class, args);
	}
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void run(String... args) throws Exception {
		
		/*
		 A CONTINUACION USUARIO ADMINISTRADOR REGISTRADO MANUALMENTE AL SISTEMA.
		 */
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre("LoginupAdmin");
			usuario.setApellido("UTN");
			usuario.setUsername("admin");
			usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
			usuario.setEmail("loginup@admin.com");
			usuario.setTelefono("5020579324");
		//Se define un rol y su nombre
			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolNombre("ADMIN");
			Set<UsuarioRol> usuariosRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);//se establece el objeto rol y usuario
			usuariosRoles.add(usuarioRol);//UsuarioRoles es un conjunto de los dos objetos
			
			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario,usuariosRoles);
			System.out.println(usuarioGuardado.getUsername());
			
			} catch (UsuarioFoundException exception) {
				exception.printStackTrace();
				//lanzara excepcion si el usuario ya existe en la bd
			}
		
	}
}

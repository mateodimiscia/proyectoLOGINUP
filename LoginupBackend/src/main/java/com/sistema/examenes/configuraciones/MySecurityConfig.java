package com.sistema.examenes.configuraciones;
import com.sistema.examenes.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//SE ENCARGA DE ENCRIPTAR LAS CONTRASEÑAS DE LOS USUARIOS
//PERMITE ESPECIFICAR LA CONFIGURACION DEL ACCESO A LOS RECURSOS.
@EnableWebSecurity
//UNA CLASE DE CONFIGURACION REGISTRA BEANS
@Configuration
/*habilita la seguridad de nivel de método de Spring.
Esta anotación nos proporciona tres mecanismos diferentes, 
prePostEnabled, secureEnabled y jsr250Enabled, para lograr la misma función.
*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
/*
 * @PreAuthorize se validará antes de que se ejecute el método
 * y la anotación @PostAuthorize se validará después de que se ejecute el método.
 */

public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {//
        return super.authenticationManagerBean();
    }
    /* 
    NO APLICA EL CIFRADO DE LAS CREDENCIALES
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    */
    
    //a la hora de guardar un usuario se encripta esa contraseña
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//se retorna la password codificada
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/generate-token","/usuarios/").permitAll()//a estas rutas se les permite
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                /*
                 * Método OTPTIONS: describe opciones de comunicacion para recursos del destino.
                 * Es muy utilizado con CORS para validar si el servidor acepta peticiones de diferentes orígenes.
                 */
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)/*
                si hay un error, le inyectamos el entry point para que llame al método.
                 */ 
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        /*se le agrega un filtro antes (before) que se encarga de validar el token en resmidas cuentas.
        Y luego se pasa otro filtro encargado de la autenticación de contraseñas.
        */
    }
}

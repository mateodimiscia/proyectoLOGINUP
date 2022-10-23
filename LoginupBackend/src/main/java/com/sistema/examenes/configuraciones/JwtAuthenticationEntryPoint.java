package com.sistema.examenes.configuraciones;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 * ESTA CLASE SE ENCARGA DE MANEJAR LOS ERRORES
 * ENVÍA ERRORES SI LOS USUARIOS NO ESTÁN AUTORIZADOS
 * ES SIMILAR A UNA CLASE DE EXCEPCION
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //MENSAJE DE ERROR 
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario no autorizado");
    }
}

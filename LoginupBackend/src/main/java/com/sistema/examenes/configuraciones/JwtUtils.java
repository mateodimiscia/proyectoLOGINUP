package com.sistema.examenes.configuraciones;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//ESTA CLASE ES UNA ESPECIE DE SERVICIO Y LOS METODOS SE LLAMAN EN FILTRO
/*
 * REPOSITORIO GITHUB PARA APOYARNOS DEL CÓDIGO Y CREAR CLASE JWTUTILS
 * https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
 * Se encarga de: generar token, crearlo, validarlo, extraer el usuario de un token mediante métodos, fecha de expiración del token,etc
 * 
 */
@Component
//SE LE INDICA QUE ES UN COMPONENTE PARA INYECTARLO EN OTRA CLASE.
public class JwtUtils {
//LLAVE SECRETA O KEY PARA LLEVAR A CABO ESTOS PROCEDIMIENTOS: GENERAR FECHA, TOKEN
    private String SECRET_KEY = "loginupweb";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
//SE ENCARGA DE LA EXTRACCIÓN DE LA EXPIRACIÓN DEL TOKEN
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
//SE CREA UN TOKEN
//SIEMPRE EL TOKEN DEBE COMENZAR CON BEARER
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 horas en milisegundos expira el token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
/*
SI EL USUARIO COINCIDE CON EL TOKEN, RETORNA VALOR TRUE LO QUE INDICA QUE ES VÁLIDO,
DE LO CONTRARIO EL TOKEN ES INVÁLIDO. 
*/
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

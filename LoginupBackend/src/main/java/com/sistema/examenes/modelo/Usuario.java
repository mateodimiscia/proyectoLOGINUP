package com.sistema.examenes.modelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//Entity sirve para que sea una entidad y se mapee con la base de datos
@Entity
// table es ara indicarle el nombre de la tabla a la base de datos
@Table(name = "usuarios")
/*
 * SE IMPLEMENTA LA INTERFAZ USERDETAILS
 * LA IMPLEMENTACIÓN SE HACEN CON LAS INTERFACES Y LAS EXTENSIONES CON LAS
 * CLASES
 */
public class Usuario implements UserDetails {

    @Id // un id le indica al campo que será unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que a cada nuevo registro sea autoincrementable
    // CAMPOS
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String apellido;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String telefono;
    @Getter
    @Setter
    private boolean enabled = true; // para habilitar o deshabilitar usuarios

    /*
     * UNA OPERACIÓN DE TIPO CASCADA ALL DONDE SE APLICAN TODOS LOS TIPOS DE
     * CASCADA.
     * Por ejemplo, en una relación Usuario-Dirección, si eliminamos el Usuario, la
     * entidad Dirección no tiene ningún sentido.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    /*
     * EAGER (ansioso) enviará registros en donde este relacionada la entidad sin
     * necesidad de un método.
     * mappedBy apunta a la entidad propietaria de la relación. En este caso es
     * usuario.
     */
    @JsonIgnore // EVITA OBTENER INFINIDAD DE DATOS SIN REPETIRSE. SE UTILIZA EN LOS METODOS GET PARA EVITAR CONFLICTOS.
    @Getter
    @Setter
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Usuario() {

    }
//Variables que se extienden a la interfaz interfaces.ts que toma y muestra los datos registrados en el frontend
    public Usuario(Long id, String username, String password, String nombre, String apellido, String email,
            String telefono, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.enabled = enabled;
    }
/*
 * Indica si la cuenta si la cuenta del usuario expiró.
 * De ser así no puede ser autenticada
 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
/*
 * Indica si el usuario fue bloqueado o desbloqueado.
 * Si el usuario es bloqueado no puede ser autenticado.
 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
/*
 * Indica si las credenciales expiraron. De ser así no pueden identificarse.
 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * LA ANOTACIÓN OVERRIDE SOBREESCRIBE MÉTODOS E INTERFACES IMPLEMENTADAS.
     */
    @Override
    /*
     * Clase que implementa para que pueda sobreescribir métodos tales como
     * el de obtener autridad par pasarle el campo autority
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {//método que obtiene los roles
        Set<Authority> autoridades = new HashSet<>();
        //bucle for each
        this.usuarioRoles.forEach(usuarioRol -> {
            //se recorre la tabla de UsuarioRoles, obtener su nombre y retornarlos
            autoridades.add(new Authority(usuarioRol.getRol().getRolNombre()));
        });
        //se retorna la autoridad
        return autoridades;
    }
}
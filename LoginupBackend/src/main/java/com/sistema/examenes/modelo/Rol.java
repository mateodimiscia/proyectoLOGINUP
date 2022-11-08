package com.sistema.examenes.modelo;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @Getter
    @Setter
    private Long rolId;
    @Getter
    @Setter
    private String rolNombre;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    @Getter
    @Setter
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
    /*
     * es de tipo cascada por si eliminamos este rol tambien se elimine el rol que estaba relacionado a los demás usuarios.
     * Cuando se realiza una operación a un registro de la tabla, este se ve afectado en las demás tablas relacionadas.
     * ES DE TIPO LAZY ya que cuando se quiera hacer una busqueda se debe indicarle.
     */

    public Rol(){
    }
    public Rol(Long rolId, String rolNombre) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }
}

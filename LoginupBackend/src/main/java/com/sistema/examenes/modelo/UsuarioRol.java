package com.sistema.examenes.modelo;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Usuario usuario;// MUCHOS ROLES LE VAN A PODER PERTENECVER A ESTE USUARIO.

    @ManyToOne
    @Getter
    @Setter
    private Rol rol;

    /*
     * POR AMBAS ENTIDADES TANTO COMO USUARIO Y ROL, ES DE UNO A MUCHOS.
     * UN USUARIO PUEDE TENER MAS DE UN ROL.
     * muchos usarios pueden pertenecerle a un solo rol
     */

}

package com.excepciones;

/*
 * CLASE PERSONALIZADA DE EXCEPCIONES
 */

public class UsuarioFoundException extends Exception {
    public UsuarioFoundException(){
     //este es el mensaje que se visualia en el login
        super("El nombre de usuario ya está registrado en la base de datos.");
    }
//este es el mensaje por si se quiere pasar un mensaje personalizado.
    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}

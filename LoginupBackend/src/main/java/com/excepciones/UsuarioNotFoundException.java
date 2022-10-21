package com.excepciones;
/*
 * CLASE PERSONALIZADA DE EXCEPCIONES
 */
public class UsuarioNotFoundException extends Exception {

        public UsuarioNotFoundException(){
           //este es el mensaje por defecto que se ve en el frontend
            super("El usuario con ese nombre de usuario no existe en la base de datos.");
        }
    //este es el mensaje por si se quiere pasar uno personalizado.
        public UsuarioNotFoundException(String mensaje){
            super(mensaje);
        }
    }
    


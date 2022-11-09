package com.excepciones;
/*
 * CLASE PERSONALIZADA DE EXCEPCIONES
 */
public class UsuarioNotFoundException extends Exception {

        public UsuarioNotFoundException(){
           //este es el mensaje por defecto que se ve en el frontend
            super("El nombre de usuario no existe en la base de datos.");
        }
        }
    


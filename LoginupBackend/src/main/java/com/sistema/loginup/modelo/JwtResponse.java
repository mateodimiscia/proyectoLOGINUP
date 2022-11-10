package com.sistema.loginup.modelo;
import lombok.Setter;
import lombok.Getter;
//Constructor que contiene el token, que es retornado.
public class JwtResponse {
    @Getter
    @Setter
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }
}

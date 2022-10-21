package com.sistema.examenes.modelo;
import lombok.Setter;
import lombok.Getter;

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

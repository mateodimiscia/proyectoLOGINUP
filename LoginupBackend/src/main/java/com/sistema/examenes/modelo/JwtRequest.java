package com.sistema.examenes.modelo;

import lombok.Getter;
import lombok.Setter;

public class JwtRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

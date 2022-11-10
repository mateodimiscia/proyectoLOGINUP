package com.sistema.loginup.modelo;

import lombok.Getter;
import lombok.Setter;

public class JwtRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    //se utiliza cuando se realiza una petición
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

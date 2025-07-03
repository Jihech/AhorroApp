package com.sise.ahorroapp.model;

public class LoginRequest {
    private String correo;
    private String clave;

    public LoginRequest(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }
}

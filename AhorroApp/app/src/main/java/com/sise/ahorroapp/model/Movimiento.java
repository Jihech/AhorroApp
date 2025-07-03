package com.sise.ahorroapp.model;

public class Movimiento {

    private Long id;
    private String tipo;
    private double monto;
    private String descripcion;
    private String fecha; // este puede estar solo como respuesta opcional

    private Long usuarioId; // si necesitas asociarlo al usuario

    // Constructor vacío (obligatorio para Retrofit o Gson)
    public Movimiento() {}

    // Constructor sin fecha (para registrar)
    public Movimiento(String tipo, double monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

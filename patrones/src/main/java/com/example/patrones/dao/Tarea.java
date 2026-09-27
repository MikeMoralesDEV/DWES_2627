package com.example.patrones.dao;

/**
 * Información sencilla de una tarea: un identificador y un texto.
 */
public class Tarea {

    private final int id;
    private final String descripcion;

    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return id + " - " + descripcion;
    }
}

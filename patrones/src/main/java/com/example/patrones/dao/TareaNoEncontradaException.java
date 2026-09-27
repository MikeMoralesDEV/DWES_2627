package com.example.patrones.dao;

public class TareaNoEncontradaException extends RuntimeException {

    public TareaNoEncontradaException(int id) {
        super("No existe una tarea con ID " + id);
    }
}

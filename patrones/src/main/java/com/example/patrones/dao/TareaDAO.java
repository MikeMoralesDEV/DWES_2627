package com.example.patrones.dao;

import java.util.List;

/**
 * Describe qué se puede hacer con las tareas, sin decidir dónde se guardan.
 */
public interface TareaDAO {

    void agregar(Tarea tarea);

    List<Tarea> listar();

    Tarea buscarPorId(int id);

    void actualizar(Tarea tarea);

    void eliminar(int id);
}

package com.example.patrones.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Guarda tareas en memoria. La interfaz permite cambiar esta clase por otra
 * implementación sin cambiar el código que utiliza las tareas.
 */
public class TareaDAOImpl implements TareaDAO {

    // La clave es el ID y el valor es la tarea; así se encuentra por ID fácilmente.
    private final Map<Integer, Tarea> tareas = new LinkedHashMap<>();

    @Override
    public void agregar(Tarea tarea) {
        if (tareas.containsKey(tarea.getId())) {
            throw new IllegalArgumentException("Ya existe una tarea con ID " + tarea.getId());
        }
        tareas.put(tarea.getId(), tarea);
    }

    @Override
    public List<Tarea> listar() {
        // La copia permite consultar los datos sin modificar el almacenamiento interno.
        return List.copyOf(tareas.values());
    }

    @Override
    public Tarea buscarPorId(int id) {
        Tarea tarea = tareas.get(id);
        if (tarea == null) {
            throw new TareaNoEncontradaException(id);
        }
        return tarea;
    }

    @Override
    public void actualizar(Tarea tarea) {
        // Primero comprobamos que exista: actualizar no debe crear una tarea nueva.
        buscarPorId(tarea.getId());
        tareas.put(tarea.getId(), tarea);
    }

    @Override
    public void eliminar(int id) {
        // Reutilizamos la búsqueda para informar claramente si el ID no existe.
        buscarPorId(id);
        tareas.remove(id);
    }
}

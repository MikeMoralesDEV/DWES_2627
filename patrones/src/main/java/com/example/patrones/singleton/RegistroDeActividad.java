package com.example.patrones.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene un historial compartido de actividades de la aplicación.
 *
 * <p>Es un ejemplo de Singleton: aunque distintas partes del programa pidan
 * el registro, todas reciben el mismo objeto y, por tanto, el mismo historial.</p>
 */
public class RegistroDeActividad {

    /*
     * Este atributo pertenece a la clase, no a cada objeto (por eso es static).
     * Se crea una sola vez cuando Java carga la clase.
     * "final" impide que la referencia apunte después a otro registro; el
     * contenido del objeto sí puede cambiar, por ejemplo al añadir actividades.
     */
    private static final RegistroDeActividad INSTANCIA = new RegistroDeActividad();

    /*
     * La lista pertenece a la única instancia. Cada actividad que se registre
     * se guarda aquí y podrá consultarse desde cualquier parte que obtenga
     * esa misma instancia.
     */
    private final List<String> actividades = new ArrayList<>();

    /*
     * El constructor privado impide crear objetos desde otras clases usando
     * "new RegistroDeActividad()". La propia clase sí puede usarlo para crear
     * la instancia única declarada arriba.
     */
    private RegistroDeActividad() {
    }

    /**
     * Proporciona el punto de acceso global a la instancia única.
     *
     * @return siempre el mismo registro de actividad
     */
    public static RegistroDeActividad obtenerInstancia() {
        return INSTANCIA;
    }

    /**
     * Añade una actividad al historial compartido.
     *
     * @param actividad descripción de la actividad que se quiere registrar
     */
    public void registrarActividad(String actividad) {
        actividades.add(actividad);
    }

    /**
     * Devuelve una copia no modificable del historial actual.
     *
     * <p>Así quien consulta las actividades puede leerlas sin modificar
     * directamente la lista interna del Singleton.</p>
     *
     * @return copia no modificable de las actividades registradas
     */
    public List<String> obtenerActividades() {
        return List.copyOf(actividades);
    }
}

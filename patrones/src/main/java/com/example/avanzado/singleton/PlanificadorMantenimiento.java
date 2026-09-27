package com.example.avanzado.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton que mantiene una lista compartida de tareas de mantenimiento.
 *
 * <p>Singleton se utiliza cuando se quiere ofrecer un único objeto y un punto
 * de acceso conocido a él. Aquí tiene sentido porque distintas partes de la
 * aplicación deben consultar y modificar el mismo planificador: si cada parte
 * creara uno nuevo, cada lista contendría tareas diferentes.</p>
 *
 * <p>El patrón se construye con tres elementos: una instancia estática
 * compartida, un constructor privado que impide crear otras instancias y un
 * método estático que devuelve la instancia compartida. Esta implementación
 * crea el objeto al cargar la clase; está pensada para este ejemplo sencillo
 * de un único hilo de ejecución.</p>
 */
public final class PlanificadorMantenimiento {

    /*
     * "static" hace que la referencia pertenezca a la clase y no a cada objeto.
     * "final" impide reemplazarla por otra referencia.
     */
    private static final PlanificadorMantenimiento INSTANCIA =
            new PlanificadorMantenimiento();

    // Este estado vive dentro de la instancia única y se comparte con quien la obtiene.
    private final List<String> tareas = new ArrayList<>();

    /*
     * El constructor privado bloquea la creación normal desde otras clases:
     * no se puede escribir "new PlanificadorMantenimiento()" fuera de aquí.
     */
    private PlanificadorMantenimiento() {
    }

    /**
     * Punto de acceso global: todas las llamadas devuelven la misma instancia.
     *
     * @return la única instancia de PlanificadorMantenimiento
     */
    public static PlanificadorMantenimiento obtenerInstancia() {
        return INSTANCIA;
    }

    /**
     * Añade una tarea al estado compartido del planificador.
     *
     * @param tarea descripción del trabajo de mantenimiento
     */
    public void agregarTarea(String tarea) {
        tareas.add(tarea);
    }

    /**
     * Devuelve una copia que se puede recorrer, pero no usar para alterar
     * la lista interna del Singleton.
     *
     * @return lista no modificable con las tareas actuales
     */
    public List<String> obtenerTareas() {
        return List.copyOf(tareas);
    }
}

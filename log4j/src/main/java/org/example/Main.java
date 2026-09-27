package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ejemplo introductorio de registros (logs) con Log4j 2.
    
 * <p>La aplicación simula matrículas en un curso para poder mostrar distintos
 * niveles de registro y cómo informar de una operación que no se puede
 * completar.</p>
 */
public class Main {
    /*
     * Un logger es el objeto al que pedimos que registre mensajes.
     *
     * LogManager encuentra la configuración de Log4j (en este ejemplo,
     * log4j2.xml) y nos proporciona un logger asociado a esta clase. Es buena
     * práctica tener un logger por clase y declararlo como static final.
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Punto de entrada estándar de una aplicación Java.
     * Los argumentos recibidos desde la línea de comandos se llaman "args";
     * este ejemplo no necesita utilizarlos.
     */
    public static void main(String[] args) {
        // INFO sirve para registrar acontecimientos normales de la aplicación.
        LOGGER.info("La aplicación de ejemplo de matrículas ha comenzado.");

        /*
         * Cada llamada demuestra un nivel diferente:
         * TRACE: detalle muy minucioso, útil para seguir cada paso.
         * DEBUG: información de diagnóstico para quien desarrolla el programa.
         * INFO: acontecimientos normales que interesa conocer.
         * WARN: algo inesperado o potencialmente problemático, aunque se continúa.
         * ERROR: una operación ha fallado.
         * FATAL: fallo muy grave que normalmente impide continuar.
         *
         * El nivel configurado en log4j2.xml es INFO, por lo que TRACE y DEBUG
         * no aparecerán todavía. Más abajo se explica cómo activarlos.
         */
        LOGGER.trace("Este mensaje detallado solo aparecerá con TRACE activado.");
        LOGGER.debug("Este mensaje de diagnóstico solo aparecerá con DEBUG activado.");

        // Hay suficientes plazas: esperamos que esta matrícula termine bien.
        registrarAlumno("Ana", 3);

        // Una plaza es una advertencia útil, pero no impide completar la acción.
        registrarAlumno("Luis", 1);

        /*
         * Cero plazas es una entrada no válida para la operación. El método
         * lanzará IllegalArgumentException y este catch registrará el error.
         * Se captura aquí para que el ejemplo pueda terminar normalmente.
         */
        try {
            registrarAlumno("Marta", 0);
        } catch (IllegalArgumentException exception) {
            /*
             * El primer argumento es el mensaje con marcadores {} y el
             * siguiente es el valor que ocupará el marcador. El último
             * argumento es la excepción: Log4j también registra su tipo y
             * recorrido de llamadas (stack trace), útil para diagnosticar.
             *
             * No hace falta concatenar cadenas con + para insertar valores.
             */
            LOGGER.error("No se pudo completar la matrícula. Motivo: {}",
                    exception.getMessage(),
                    exception);
        }

        LOGGER.info("La aplicación de ejemplo ha terminado.");
    }

    /**
     * Simula la matrícula de un alumno en un curso.
     *
     * @param nombreAlumno nombre de la persona que quiere matricularse
     * @param plazasDisponibles plazas libres antes de intentar la matrícula
     * @throws IllegalArgumentException si los datos no permiten matricular
     */
    private static void registrarAlumno(String nombreAlumno, int plazasDisponibles) {
        // DEBUG deja constancia de los datos usados durante el diagnóstico.
        LOGGER.debug("Comprobando matrícula: alumno={}, plazas={}",
                nombreAlumno,
                plazasDisponibles);

        // Validar los datos antes de realizar la operación.
        if (nombreAlumno == null || nombreAlumno.isBlank()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío.");
        }

        if (plazasDisponibles <= 0) {
            throw new IllegalArgumentException("No quedan plazas disponibles.");
        }

        // WARN informa de una situación a vigilar sin detener la aplicación.
        if (plazasDisponibles == 1) {
            LOGGER.warn("Solo queda una plaza cuando {} solicita la matrícula.",
                    nombreAlumno);
        }

        // INFO confirma una operación que se ha completado correctamente.
        LOGGER.info("Matrícula completada para {}.", nombreAlumno);
    }
}

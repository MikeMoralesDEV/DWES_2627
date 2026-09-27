package com.example.avanzado;

import com.example.avanzado.di.CamaraDigital;
import com.example.avanzado.di.EstudioFotografico;
import com.example.avanzado.di.ImpresoraLocal;
import com.example.avanzado.factory.BaseCanales;
import com.example.avanzado.factory.CanalNotificacion;
import com.example.avanzado.factory.CorreoFactory;
import com.example.avanzado.factory.MensajeMovilFactory;
import com.example.avanzado.singleton.PlanificadorMantenimiento;

/**
 * Punto de entrada que ensambla y muestra los tres ejemplos avanzados.
 *
 * <p>Este programa también ayuda a distinguir los patrones:</p>
 * <ul>
 *     <li>DI: se entregan a un objeto las colaboraciones que necesita.</li>
 *     <li>Factory: una fábrica decide qué implementación concreta crear.</li>
 *     <li>Singleton: se accede siempre al mismo objeto compartido.</li>
 * </ul>
 *
 * <p>Las clases de cada ejemplo contienen la implementación del patrón;
 * aquí se ve el papel del código cliente, es decir, del código que los utiliza.</p>
 */
public class Main {

    public static void main(String[] args) {
        // Cada método es una demostración independiente.
        ejemploInyeccionPorConstructor();
        ejemploFactory();
        ejemploSingleton();
    }

    private static void ejemploInyeccionPorConstructor() {
        System.out.println("=== DI por constructor: estudio fotográfico ===");

        /*
         * EstudioFotografico necesita un capturador y una impresora. Main crea
         * las implementaciones concretas y se las pasa al constructor. Por eso
         * el estudio no tiene que decidir qué cámara ni qué impresora utilizar.
         * Esta labor de crear y conectar objetos se llama composición.
         */
        CamaraDigital camara = new CamaraDigital();
        ImpresoraLocal impresora = new ImpresoraLocal();
        EstudioFotografico estudio = new EstudioFotografico(camara, impresora);

        // El estudio coordina las dependencias recibidas; no las construye.
        estudio.realizarSesion();
    }

    private static void ejemploFactory() {
        System.out.println("\n=== Factory: selección de canales de notificación ===");

        /*
         * Primero elegimos qué fábrica queremos usar. CorreoFactory y
         * MensajeMovilFactory son fábricas concretas: cada una sabe crear
         * una implementación diferente de CanalNotificacion.
         *
         * Después enviamos ambas fábricas al mismo método. La selección de una
         * fábrica cambia el producto creado, pero no cambia la forma de usarlo.
         */
        enviarAviso(new CorreoFactory(), "cliente@example.com");
        enviarAviso(new MensajeMovilFactory(), "+34123456789");
    }

    private static void enviarAviso(BaseCanales fabrica, String destinatario) {
        /*
         * Esta variable recibe cualquier fábrica que extienda BaseCanales.
         * El método no comprueba si recibió una fábrica de correo o de móvil.
         *
         * La llamada crearCanal() se ejecuta según la clase real del objeto:
         * una fábrica crea Correo y la otra crea MensajeMovil. Aunque no sabemos
         * cuál fue, el resultado se guarda como CanalNotificacion porque ambos
         * productos ofrecen el método enviar().
         */
        CanalNotificacion canal = fabrica.crearCanal();

        // Usamos el producto sin conocer su clase concreta.
        canal.enviar(destinatario, "Tu reserva ha sido confirmada.");
    }

    private static void ejemploSingleton() {
        System.out.println("\n=== Singleton: planificador compartido ===");

        /*
         * No se puede usar "new PlanificadorMantenimiento()" porque el
         * constructor es privado. Se solicita el objeto mediante el punto de
         * acceso estático obtenerInstancia().
         */
        PlanificadorMantenimiento taller =
                PlanificadorMantenimiento.obtenerInstancia();
        taller.agregarTarea("Revisar el sistema de refrigeración");

        /*
         * Simulamos otro componente de la aplicación. Al pedir la instancia
         * otra vez, recibe el mismo objeto y puede ver el estado que ya tenía.
         */
        PlanificadorMantenimiento panel =
                PlanificadorMantenimiento.obtenerInstancia();
        panel.agregarTarea("Cambiar el filtro de aire");

        // La comparación de referencias demuestra que es exactamente el mismo objeto.
        System.out.println("¿Es el mismo planificador? " + (taller == panel));

        // Las dos tareas están en una lista perteneciente a la instancia compartida.
        for (String tarea : panel.obtenerTareas()) {
            System.out.println("- " + tarea);
        }
    }

}

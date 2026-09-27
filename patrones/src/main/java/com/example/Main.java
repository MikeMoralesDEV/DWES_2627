package com.example;

import com.example.patrones.dao.Tarea;
import com.example.patrones.dao.TareaDAO;
import com.example.patrones.dao.TareaDAOImpl;
import com.example.patrones.dao.TareaNoEncontradaException;
import com.example.patrones.di.AvisoPorConsola;
import com.example.patrones.di.CanalAvisos;
import com.example.patrones.di.InyectableCanalAvisos;
import com.example.patrones.di.PanelIncidencias;
import com.example.patrones.di.ServicioReservas;
import com.example.patrones.factory.BaseFormatos;
import com.example.patrones.factory.FormatoMensaje;
import com.example.patrones.factory.MarkdownFactory;
import com.example.patrones.factory.TextoPlanoFactory;
import com.example.patrones.singleton.RegistroDeActividad;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== 1. Singleton: un registro compartido ===");
        ejemploSingleton();

        System.out.println("\n=== 2. DAO: separar el uso de los datos ===");
        ejemploDao();

        System.out.println("\n=== 3. Factory: delegar la creación de objetos ===");
        ejemploFactory();

        System.out.println("\n=== 4. DI: entregar dependencias desde fuera ===");
        ejemploInyeccionDependencias();
    }

    private static void ejemploSingleton() {
        // No se construye el registro con "new": se pide la instancia única.
        RegistroDeActividad primerAcceso = RegistroDeActividad.obtenerInstancia();
        primerAcceso.registrarActividad("Se ha iniciado la aplicación.");

        // Aunque se solicite otra vez, se recibe el mismo objeto y su historial.
        RegistroDeActividad segundoAcceso = RegistroDeActividad.obtenerInstancia();
        segundoAcceso.registrarActividad("Se ha abierto la sección de tareas.");

        System.out.println("¿Las dos referencias apuntan al mismo objeto? "
                + (primerAcceso == segundoAcceso));
        System.out.println("Historial compartido:");
        for (String actividad : segundoAcceso.obtenerActividades()) {
            System.out.println("- " + actividad);
        }
    }

    private static void ejemploDao() {
        // El resto del programa trabaja con la interfaz TareaDAO.
        // TareaDAOImpl decide que, en este ejemplo, los datos se guardan en memoria.
        TareaDAO tareas = new TareaDAOImpl();

        tareas.agregar(new Tarea(1, "Repasar los patrones"));
        tareas.agregar(new Tarea(2, "Hacer los ejercicios"));

        System.out.println("Tareas guardadas:");
        for (Tarea tarea : tareas.listar()) {
            System.out.println("- " + tarea);
        }

        // Buscar por un ID inexistente provoca una excepción descriptiva.
        try {
            tareas.buscarPorId(99);
        } catch (TareaNoEncontradaException excepcion) {
            System.out.println("Búsqueda: " + excepcion.getMessage());
        }
    }

    private static void ejemploFactory() {
        /*
         * Factory tiene dos pasos que conviene distinguir:
         *
         * 1. Se elige una fábrica concreta según el formato que se necesita.
         * 2. Se pide a esa fábrica el producto y se usa mediante su interfaz.
         *
         * La variable "fabrica" tiene el tipo común BaseFormatos. Al cambiar
         * la fábrica elegida, cambia el producto creado sin cambiar el código
         * que lo utiliza.
         */
        BaseFormatos fabrica = new MarkdownFactory();
        FormatoMensaje formato = fabrica.crearFormato();

        /*
         * Aquí no hacemos "new Markdown()". Esa decisión está dentro de
         * MarkdownFactory. Main solo recibe un FormatoMensaje y sabe que puede
         * llamar a formatear(), sin necesitar saber la clase concreta.
         */
        System.out.println("Con la fábrica Markdown: "
                + formato.formatear("Patrón Factory"));

        /*
         * Para obtener texto plano, cambiamos qué fábrica seleccionamos.
         * La forma de pedir y utilizar el producto permanece igual.
         */
        fabrica = new TextoPlanoFactory();
        formato = fabrica.crearFormato();
        System.out.println("Con la fábrica de texto plano: "
                + formato.formatear("Patrón Factory"));
    }

    private static void ejemploInyeccionDependencias() {
        /*
         * CanalAvisos es una dependencia: ServicioReservas la necesita para
         * enviar la confirmación. Main crea el canal y se lo entrega al
         * servicio por el constructor. El servicio no crea el canal por sí mismo.
         */
        CanalAvisos canal = new AvisoPorConsola();
        ServicioReservas reservas = new ServicioReservas(canal);
        reservas.confirmarReserva("Ana", "Sala azul");

        /*
         * Aquí vemos la inyección por interfaz: PanelIncidencias implementa
         * InyectableCanalAvisos para declarar que acepta un CanalAvisos.
         * Se crea el panel y después se le entrega esa dependencia llamando
         * al método definido en la interfaz.
         */
        PanelIncidencias panel = new PanelIncidencias();
        InyectableCanalAvisos componenteConfigurable = panel;
        componenteConfigurable.inyectarCanalAvisos(canal);
        panel.registrarIncidencia("La puerta no abre");
    }
}

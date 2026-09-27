package com.example.avanzado.di;

/**
 * Coordina las tareas de un estudio: obtener una imagen e imprimirla.
 *
 * <p>Esta clase tiene dependencias porque necesita otros objetos para trabajar:
 * un Capturador y una Impresora. No los construye internamente. Recibirlos
 * desde fuera es Inyección de Dependencias (DI).</p>
 */
public class EstudioFotografico {

    // Se guardan las dependencias recibidas para reutilizarlas durante la sesión.
    private final Capturador capturador;
    private final Impresora impresora;

    /**
     * Inyección por constructor: las dependencias se entregan al crear el
     * estudio. Al terminar el constructor, el objeto ya está preparado para usar.
     *
     * <p>Los parámetros son interfaces, no clases concretas. Esto permite
     * cambiar la cámara o la impresora sin reescribir el proceso del estudio.</p>
     *
     * @param capturador objeto que proporcionará la imagen
     * @param impresora objeto que recibirá la imagen para imprimirla
     */
    public EstudioFotografico(Capturador capturador, Impresora impresora) {
        this.capturador = capturador;
        this.impresora = impresora;
    }

    /**
     * Ejecuta una sesión usando primero el capturador y luego la impresora.
     */
    public void realizarSesion() {
        // Se pide la imagen a una dependencia, sin conocer su implementación.
        String imagen = capturador.capturar();

        // El resultado de la captura pasa a la otra dependencia.
        impresora.imprimir(imagen);
    }
}

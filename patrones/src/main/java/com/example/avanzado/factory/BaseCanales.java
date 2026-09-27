package com.example.avanzado.factory;

/**
 * Clase base de las fábricas de canales de notificación.
 *
 * <p>En Factory se separa la creación de un objeto de su uso. Si el cliente
 * escribiera {@code new Correo()} o {@code new MensajeMovil()}, tendría que
 * conocer cada clase concreta y decidir por sí mismo cuál crear. En este
 * ejemplo, el cliente elige una fábrica y la fábrica crea el producto.</p>
 *
 * <p>Las subclases implementan {@link #crearCanal()} de forma distinta. El
 * método sobrescrito es el método de fábrica: devuelve el producto común
 * {@link CanalNotificacion}, pero cada fábrica decide la clase concreta.</p>
 */
public abstract class BaseCanales {

    /**
     * Crea el producto que corresponde a la fábrica concreta utilizada.
     *
     * @return canal de notificación que el cliente podrá utilizar
     */
    public abstract CanalNotificacion crearCanal();
}

package com.example.avanzado.factory;

/**
 * Producto abstracto del ejemplo Factory.
 *
 * <p>Un producto es el objeto que la fábrica entrega al código cliente. Todos
 * los productos de este ejemplo implementan CanalNotificacion, aunque cada uno
 * envía el aviso por un medio distinto. El cliente puede invocar enviar()
 * sin conocer la clase concreta que recibió.</p>
 */
public interface CanalNotificacion {

    /**
     * Envía un mensaje al destinatario por el canal concreto.
     *
     * @param destinatario persona o dirección que recibirá el aviso
     * @param mensaje contenido del aviso
     */
    void enviar(String destinatario, String mensaje);
}

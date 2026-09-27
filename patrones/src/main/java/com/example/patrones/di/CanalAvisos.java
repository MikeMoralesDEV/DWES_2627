package com.example.patrones.di;

/**
 * Abstracción de algo capaz de enviar un aviso.
 *
 * <p>Los servicios dependen de esta interfaz, no de un canal concreto como
 * correo, consola o una aplicación de mensajería.</p>
 */
public interface CanalAvisos {

    void enviar(String destinatario, String mensaje);
}

package com.example.avanzado.factory;

/**
 * Producto concreto de Factory que representa el envío a un teléfono móvil.
 *
 * <p>Lo crea {@link MensajeMovilFactory}. Comparte la interfaz
 * {@link CanalNotificacion} con {@link Correo}, por eso el código cliente
 * puede utilizar ambos productos del mismo modo.</p>
 */
public class MensajeMovil implements CanalNotificacion {

    @Override
    public void enviar(String destinatario, String mensaje) {
        // El envío se simula para centrar el ejemplo en la creación del producto.
        System.out.println("Mensaje móvil para " + destinatario + ": " + mensaje);
    }
}

package com.example.avanzado.factory;

/**
 * Producto concreto de Factory que representa el envío por correo.
 *
 * <p>Lo crea {@link CorreoFactory}. Main no necesita construirlo directamente:
 * recibe el objeto como CanalNotificacion y utiliza la operación común enviar.</p>
 */
public class Correo implements CanalNotificacion {

    @Override
    public void enviar(String destinatario, String mensaje) {
        // El envío se simula para centrar el ejemplo en la creación del producto.
        System.out.println("Correo para " + destinatario + ": " + mensaje);
    }
}

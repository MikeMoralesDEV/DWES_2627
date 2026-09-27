package com.example.patrones.factory;

/**
 * Producto concreto que presenta el mensaje sin añadir marcas de formato.
 */
public class TextoPlano implements FormatoMensaje {

    @Override
    public String formatear(String mensaje) {
        return mensaje;
    }
}

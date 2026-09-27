package com.example.patrones.factory;

/**
 * Producto concreto que presenta el mensaje como un encabezado de Markdown.
 */
public class Markdown implements FormatoMensaje {

    @Override
    public String formatear(String mensaje) {
        return "# " + mensaje;
    }
}

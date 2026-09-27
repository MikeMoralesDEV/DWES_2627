package com.example.patrones.factory;

/**
 * Producto común del patrón Factory.
 *
 * <p>Las implementaciones concretas (por ejemplo, {@link TextoPlano} y
 * {@link Markdown}) ofrecen la misma operación, aunque cada una presenta
 * el mensaje de forma distinta. Así el código cliente depende de esta
 * interfaz y no necesita conocer la clase concreta que recibió de la fábrica.</p>
 */
public interface FormatoMensaje {

    /**
     * Presenta el mensaje según las reglas de este formato.
     *
     * @param mensaje texto que se quiere presentar
     * @return mensaje transformado al formato correspondiente
     */
    String formatear(String mensaje);
}

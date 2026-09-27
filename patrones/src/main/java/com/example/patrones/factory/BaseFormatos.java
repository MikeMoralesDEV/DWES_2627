package com.example.patrones.factory;

/**
 * Es la base común de las fábricas de formatos.
 *
 * <p>El patrón Factory separa la decisión de qué clase concreta crear del
 * código que necesita utilizar el objeto. En este ejemplo, ese objeto es un
 * {@link FormatoMensaje}: puede ser texto plano o Markdown.</p>
 *
 * <p>Las clases cliente pueden trabajar con esta base y pedir un formato sin
 * ejecutar directamente {@code new TextoPlano()} ni {@code new Markdown()}.
 * La subclase concreta de la fábrica decide qué implementación devolver.</p>
 */
public abstract class BaseFormatos {

    /**
     * Método de fábrica: las fábricas concretas lo sobrescriben para elegir
     * y crear el producto adecuado.
     *
     * @return un formato que el cliente usará a través de la interfaz común
     */
    public abstract FormatoMensaje crearFormato();
}

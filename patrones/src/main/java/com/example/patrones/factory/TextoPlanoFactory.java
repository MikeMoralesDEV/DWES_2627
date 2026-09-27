package com.example.patrones.factory;

/**
 * Fábrica concreta: decide crear el producto {@link TextoPlano}.
 *
 * <p>Para añadir este formato, el cliente selecciona esta fábrica. La creación
 * queda encapsulada aquí, en lugar de repartir {@code new TextoPlano()} por
 * el código cliente.</p>
 */
public class TextoPlanoFactory extends BaseFormatos {

    @Override
    public FormatoMensaje crearFormato() {
        // El tipo de retorno es la interfaz común, no la clase concreta.
        return new TextoPlano();
    }
}

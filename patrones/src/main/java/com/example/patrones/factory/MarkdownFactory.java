package com.example.patrones.factory;

/**
 * Fábrica concreta: decide crear el producto {@link Markdown}.
 *
 * <p>Es otra manera de crear un {@link FormatoMensaje}; quien recibe el
 * producto puede usarlo igual que el producto de {@link TextoPlanoFactory}.</p>
 */
public class MarkdownFactory extends BaseFormatos {

    @Override
    public FormatoMensaje crearFormato() {
        // La decisión de crear Markdown se mantiene dentro de esta fábrica.
        return new Markdown();
    }
}

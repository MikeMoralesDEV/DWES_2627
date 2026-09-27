package com.example.patrones.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseFormatosTest {

    @Test
    void fabricaTextoPlano() {
        BaseFormatos fabrica = new TextoPlanoFactory();
        FormatoMensaje formato = fabrica.crearFormato();

        assertEquals("Hola", formato.formatear("Hola"));
    }

    @Test
    void fabricaMarkdown() {
        BaseFormatos fabrica = new MarkdownFactory();
        FormatoMensaje formato = fabrica.crearFormato();

        assertEquals("# Hola", formato.formatear("Hola"));
    }
}

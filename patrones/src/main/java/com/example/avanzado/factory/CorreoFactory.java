package com.example.avanzado.factory;

/**
 * Fábrica concreta que crea productos de tipo {@link Correo}.
 *
 * <p>La decisión de instanciar Correo queda aquí. El código cliente solo
 * necesita conocer la fábrica base y el tipo de producto común.</p>
 */
public class CorreoFactory extends BaseCanales {

    @Override
    public CanalNotificacion crearCanal() {
        // El resultado se expone como interfaz, no como clase concreta.
        return new Correo();
    }
}

package com.example.avanzado.factory;

/**
 * Fábrica concreta que crea productos de tipo {@link MensajeMovil}.
 *
 * <p>Esta clase puede cambiar la implementación creada sin que el método
 * cliente que usa CanalNotificacion tenga que cambiar.</p>
 */
public class MensajeMovilFactory extends BaseCanales {

    @Override
    public CanalNotificacion crearCanal() {
        // Aquí se decide cuál es la clase concreta asociada a esta fábrica.
        return new MensajeMovil();
    }
}

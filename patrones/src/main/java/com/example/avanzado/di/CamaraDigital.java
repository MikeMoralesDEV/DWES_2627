package com.example.avanzado.di;

/**
 * Implementación concreta de {@link Capturador} que simula una cámara digital.
 *
 * <p>La clase define cómo se realiza la captura en esta versión. Otra clase
 * podría implementar Capturador de otra manera y ser entregada al estudio
 * sin modificar {@link EstudioFotografico}.</p>
 */
public class CamaraDigital implements Capturador {

    @Override
    public String capturar() {
        // Para mantener el ejemplo centrado en DI, la imagen se representa con texto.
        return "fotografía digital";
    }
}

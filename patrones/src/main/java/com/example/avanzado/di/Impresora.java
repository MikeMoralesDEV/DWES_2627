package com.example.avanzado.di;

/**
 * Contrato común para cualquier dispositivo que pueda imprimir una imagen.
 *
 * <p>El estudio conoce este contrato y solo necesita saber que puede pedir
 * una impresión. No necesita conocer si la implementación es local, de red
 * o si en otro contexto guarda la imagen como archivo.</p>
 */
public interface Impresora {

    /**
     * Envía una imagen al dispositivo de impresión.
     *
     * @param imagen representación de la imagen que se quiere imprimir
     */
    void imprimir(String imagen);
}

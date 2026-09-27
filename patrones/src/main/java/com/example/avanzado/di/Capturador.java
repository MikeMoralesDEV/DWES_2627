package com.example.avanzado.di;

/**
 * Contrato que deben cumplir los objetos capaces de obtener una imagen.
 *
 * <p>Esta interfaz es una abstracción: describe qué operación ofrece el
 * dispositivo, pero no cómo se realiza la captura. Una cámara real, una cámara
 * web o un lector de imágenes podrían ofrecer implementaciones diferentes.</p>
 *
 * <p>{@link EstudioFotografico} depende de este tipo general. Así no queda
 * acoplado a {@link CamaraDigital} ni necesita cambiar si se añade otro
 * capturador.</p>
 */
public interface Capturador {

    /**
     * Obtiene una imagen.
     *
     * @return representación de la imagen capturada
     */
    String capturar();
}

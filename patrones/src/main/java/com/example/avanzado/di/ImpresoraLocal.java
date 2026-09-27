package com.example.avanzado.di;

/**
 * Implementación concreta de {@link Impresora} que muestra la impresión
 * simulada en la consola.
 *
 * <p>El estudio puede utilizar esta implementación porque cumple el contrato
 * de Impresora. La clase que llama al constructor del estudio decide si usa
 * esta impresora u otra.</p>
 */
public class ImpresoraLocal implements Impresora {

    @Override
    public void imprimir(String imagen) {
        // Una impresora real enviaría los datos al dispositivo físico.
        System.out.println("Imprimiendo: " + imagen);
    }
}

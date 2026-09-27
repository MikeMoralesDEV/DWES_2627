package com.example.patrones.di;

/**
 * Canal sencillo para el ejemplo: muestra los avisos en la consola.
 */
public class AvisoPorConsola implements CanalAvisos {

    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("Aviso para " + destinatario + ": " + mensaje);
    }
}

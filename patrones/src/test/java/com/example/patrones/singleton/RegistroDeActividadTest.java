package com.example.patrones.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistroDeActividadTest {

    @Test
    void obtenerInstanciaSiempreDevuelveElMismoObjeto() {
        RegistroDeActividad primeraReferencia = RegistroDeActividad.obtenerInstancia();
        RegistroDeActividad segundaReferencia = RegistroDeActividad.obtenerInstancia();

        assertTrue(primeraReferencia == segundaReferencia);
    }

    @Test
    void todasLasReferenciasCompartenElHistorial() {
        RegistroDeActividad primeraReferencia = RegistroDeActividad.obtenerInstancia();
        RegistroDeActividad segundaReferencia = RegistroDeActividad.obtenerInstancia();
        String actividad = "prueba-" + System.nanoTime();

        primeraReferencia.registrarActividad(actividad);

        assertTrue(segundaReferencia.obtenerActividades().contains(actividad));
        assertEquals(primeraReferencia.obtenerActividades(),
                segundaReferencia.obtenerActividades());
    }
}

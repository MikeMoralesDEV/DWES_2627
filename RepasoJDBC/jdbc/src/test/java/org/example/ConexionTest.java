package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class ConexionTest {

    @Test
    void devuelveSiempreLaMismaInstancia() {
        Conexion primera = Conexion.getInstancia();
        Conexion segunda = Conexion.getInstancia();

        assertSame(primera, segunda);
    }
}

package com.example.patrones.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TareaDAOImplTest {

    @Test
    void permiteAgregarConsultarActualizarYEliminarUnaTarea() {
        TareaDAO dao = new TareaDAOImpl();

        dao.agregar(new Tarea(1, "Preparar café"));
        assertEquals("Preparar café", dao.buscarPorId(1).getDescripcion());
        assertEquals(1, dao.listar().size());

        dao.actualizar(new Tarea(1, "Preparar té"));
        assertEquals("Preparar té", dao.buscarPorId(1).getDescripcion());

        dao.eliminar(1);
        assertEquals(0, dao.listar().size());
    }

    @Test
    void informaCuandoSeBuscaUnaTareaInexistente() {
        TareaDAO dao = new TareaDAOImpl();

        assertThrows(TareaNoEncontradaException.class, () -> dao.buscarPorId(7));
    }
}

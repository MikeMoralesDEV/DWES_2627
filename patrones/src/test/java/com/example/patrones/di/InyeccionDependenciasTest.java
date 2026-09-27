package com.example.patrones.di;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InyeccionDependenciasTest {

    @Test
    void servicioReservasRecibeElCanalPorConstructor() {
        List<String> avisos = new ArrayList<>();
        CanalAvisos canal = (destinatario, mensaje) ->
                avisos.add(destinatario + ": " + mensaje);
        ServicioReservas servicio = new ServicioReservas(canal);

        servicio.confirmarReserva("Ana", "Sala azul");

        assertEquals(List.of("Ana: La reserva de Sala azul está confirmada."), avisos);
    }

    @Test
    void panelIncidenciasRecibeElCanalMedianteSuInterfazDeInyeccion() {
        List<String> avisos = new ArrayList<>();
        CanalAvisos canal = (destinatario, mensaje) ->
                avisos.add(destinatario + ": " + mensaje);
        InyectableCanalAvisos componenteConfigurable = new PanelIncidencias();
        componenteConfigurable.inyectarCanalAvisos(canal);

        ((PanelIncidencias) componenteConfigurable).registrarIncidencia("La puerta no abre");

        assertEquals(List.of("soporte: Nueva incidencia: La puerta no abre"), avisos);
    }

    @Test
    void panelIndicaSiSeUsaAntesDeRecibirLaDependencia() {
        PanelIncidencias panel = new PanelIncidencias();

        assertThrows(IllegalStateException.class,
                () -> panel.registrarIncidencia("La puerta no abre"));
    }
}

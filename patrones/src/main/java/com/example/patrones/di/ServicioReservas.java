package com.example.patrones.di;

/**
 * Gestiona confirmaciones de reservas y necesita un canal para avisar.
 */
public class ServicioReservas {

    private final CanalAvisos canalAvisos;

    /**
     * Inyección por constructor: quien crea el servicio le entrega la
     * dependencia que necesita. Así el servicio no elige ni construye el canal.
     */
    public ServicioReservas(CanalAvisos canalAvisos) {
        this.canalAvisos = canalAvisos;
    }

    public void confirmarReserva(String cliente, String sala) {
        canalAvisos.enviar(cliente, "La reserva de " + sala + " está confirmada.");
    }
}

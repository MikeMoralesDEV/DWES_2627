package com.example.patrones.di;

/**
 * Permite registrar incidencias y enviar un aviso al equipo de soporte.
 */
public class PanelIncidencias implements InyectableCanalAvisos {

    private CanalAvisos canalAvisos;

    /**
     * Inyección por interfaz: esta clase ofrece un método público para recibir
     * el canal. Puede construirse primero y configurarse después.
     */
    @Override
    public void inyectarCanalAvisos(CanalAvisos canalAvisos) {
        this.canalAvisos = canalAvisos;
    }

    public void registrarIncidencia(String descripcion) {
        if (canalAvisos == null) {
            throw new IllegalStateException(
                    "Hay que inyectar un canal de avisos antes de registrar incidencias.");
        }

        canalAvisos.enviar("soporte", "Nueva incidencia: " + descripcion);
    }
}

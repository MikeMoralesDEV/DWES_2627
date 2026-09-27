package com.example.patrones.di;

/**
 * Contrato de inyección por interfaz.
 *
 * <p>Una clase que implemente esta interfaz declara que puede recibir un
 * {@link CanalAvisos}. El código que configura la aplicación le proporciona
 * la dependencia llamando a {@link #inyectarCanalAvisos(CanalAvisos)}.</p>
 */
public interface InyectableCanalAvisos {

    void inyectarCanalAvisos(CanalAvisos canalAvisos);
}

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Esta clase aplica el patrón Singleton: su constructor es privado y solo
 * existe una instancia, que se obtiene mediante getInstancia(). Así todos
 * los DAO utilizan el mismo objeto encargado de abrir y cerrar la conexión
 * con la base de datos, en lugar de crear conexiones sin control.
 */
public final class Conexion {

    /*
     * En una aplicación más grande, estos datos podrían estar en
     * src/main/resources/database.properties y cargarse con Properties.
     * Así la configuración se separa del código y se puede cambiar la base
     * de datos sin modificar ni recompilar esta clase.
     *
     * Ejemplo de database.properties:
     * db.url=jdbc:mariadb://localhost:3306/classicmodels
     * db.user=root
     * db.password=
     */
    /*
     * jdbc:mariadb identifica el protocolo JDBC y el controlador de MariaDB.
     * El controlador no forma parte de Java: lo proporciona el JAR
     * mariadb-java-client, declarado como dependencia en el pom.xml.
     * Después aparecen el servidor, el puerto y la base de datos.
     */
    private static final String URL =
            "jdbc:mariadb://localhost:3306/classicmodels";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public synchronized Connection getConexion() throws SQLException {
        // DriverManager utiliza el controlador MariaDB para abrir la conexión.
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }
        return conexion;
    }

    public void cerrar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}

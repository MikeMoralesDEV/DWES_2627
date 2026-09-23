package org.example.dao;

import org.example.Conexion;
import org.example.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Esta clase es un DAO porque concentra el acceso a datos de employees:
 * construye las consultas SQL, las ejecuta y transforma sus resultados en
 * objetos Employee. Se encuentra en el paquete/directorio dao para separar
 * la persistencia de la lógica de la aplicación y del modelo.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    /*
     * Esto es un text block: una cadena de texto delimitada por tres comillas
     * dobles. Se introdujo en Java 15 y permite escribir texto de varias
     * líneas, como una consulta SQL, conservando su formato y evitando unir
     * muchas cadenas con el operador +.
     */
    private static final String BASE_SELECT = """
            SELECT employeeNumber, lastName, firstName, extension, email,
                   officeCode, reportsTo, jobTitle
            FROM employees
            """;

    private static final String INSERT_SQL = """
            INSERT INTO employees
                (employeeNumber, lastName, firstName, extension, email,
                 officeCode, reportsTo, jobTitle)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private final Conexion conexion;

    public EmployeeDAOImpl() {
        /*
         * Se obtiene el Singleton porque Conexion es el único objeto que
         * controla la conexión compartida con la base de datos.
         */
        this.conexion = Conexion.getInstancia();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        /*
         * PreparedStatement representa una sentencia SQL preparada. Es
         * similar a Statement, pero permite usar parámetros (?) y separa
         * el SQL de los valores, evitando concatenaciones inseguras.
         * ResultSet es el cursor que contiene las filas devueltas por SELECT.
         */
        String sql = BASE_SELECT + "ORDER BY employeeNumber";
        List<Employee> employees = new ArrayList<>();

        Connection connection = conexion.getConexion();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapRow(resultSet));
            }
        }

        return employees;
    }

    @Override
    public Optional<Employee> findById(int employeeNumber)
            throws SQLException {
        /*
         * Aquí PreparedStatement permite enviar employeeNumber mediante
         * setInt(), en lugar de concatenarlo en el texto SQL. El primer ?
         * de la consulta es el parámetro número 1, por eso se escribe
         * statement.setInt(1, employeeNumber). Los parámetros se numeran
         * desde 1, no desde 0.
         *
         * Si hubiera más de un parámetro, cada ? tendría su propia posición:
         *
         *   String sql = "SELECT ... FROM employees "
         *           + "WHERE officeCode = ? AND jobTitle = ?";
         *   statement.setString(1, "1");       // primer ?
         *   statement.setString(2, "Sales Rep"); // segundo ?
         *
         * Statement ejecutaría una cadena completa y sería menos apropiado
         * para datos recibidos como parámetros. El ResultSet tendrá cero o
         * una fila.
         */
        String sql = BASE_SELECT + "WHERE employeeNumber = ?";

        Connection connection = conexion.getConexion();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, employeeNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }

        /*
         * Optional.empty() representa un Optional que no contiene ningún
         * Employee. Se devuelve cuando no existe un empleado con ese número.
         * No significa que haya ocurrido un error: significa que la consulta
         * terminó correctamente, pero no encontró resultados.
         */
        return Optional.empty();
    }

    @Override
    public List<Employee> findByOfficeCode(String officeCode)
            throws SQLException {
        /*
         * Se vuelve a utilizar PreparedStatement para insertar officeCode
         * de forma segura. executeQuery() devuelve un ResultSet; next()
         * avanza fila a fila hasta que no quedan resultados.
         */
        String sql = BASE_SELECT + "WHERE officeCode = ? "
                + "ORDER BY employeeNumber";
        List<Employee> employees = new ArrayList<>();

        Connection connection = conexion.getConexion();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, officeCode);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employees.add(mapRow(resultSet));
                }
            }
        }

        return employees;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        /*
         * Para INSERT se utiliza executeUpdate(), no executeQuery().
         * executeUpdate() devuelve el número de filas afectadas. Los ocho
         * parámetros se asignan en el mismo orden que los ocho signos ? de
         * INSERT_SQL, empezando siempre por la posición 1.
         *
         * reportsTo puede ser null porque la columna permite NULL. En ese
         * caso se utiliza setNull() para enviar correctamente un NULL SQL;
         * cuando contiene un número se utiliza setInt().
         */
        Connection connection = conexion.getConexion();

        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_SQL)) {
            statement.setInt(1, employee.getEmployeeNumber());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getExtension());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getOfficeCode());

            if (employee.getReportsTo() == null) {
                statement.setNull(7, java.sql.Types.INTEGER);
            } else {
                statement.setInt(7, employee.getReportsTo());
            }

            statement.setString(8, employee.getJobTitle());

            return statement.executeUpdate() == 1;
        }
    }

    private Employee mapRow(ResultSet resultSet) throws SQLException {
        /*
         * mapRow realiza el mapeo manual entre una fila del ResultSet y un
         * objeto Employee. Los nombres usados en getInt/getString son las
         * columnas devueltas por el SELECT.
         *
         * getInt() devuelve 0 cuando una columna SQL contiene NULL. Esto
         * puede confundirnos, porque 0 podría parecer un número válido. Por
         * eso se llama inmediatamente a wasNull(): devuelve true si el último
         * valor leído era SQL NULL.
         *
         * Ejemplo:
         *
         *   reportsTo = 1500  -> wasNull() es false -> se guarda 1500
         *   reportsTo = NULL  -> getInt() da 0, pero wasNull() es true
         *                         -> se guarda null
         *
         * En el segundo caso no queremos guardar 0, sino conservar que en la
         * base de datos no había ningún responsable. Se utiliza Integer en
         * lugar de int porque Integer puede guardar un número o null, mientras
         * que int siempre tiene que contener un número.
         *
         * Los frameworks modernos, como JPA/Hibernate o Spring Data, suelen
         * hacer este mapeo automáticamente mediante reflexión o anotaciones;
         * aquí lo escribimos explícitamente para aprender cómo funciona JDBC.
         */
        int reportsTo = resultSet.getInt("reportsTo");
        Integer reportsToValue = resultSet.wasNull() ? null : reportsTo;

        return new Employee(
                resultSet.getInt("employeeNumber"),
                resultSet.getString("lastName"),
                resultSet.getString("firstName"),
                resultSet.getString("extension"),
                resultSet.getString("email"),
                resultSet.getString("officeCode"),
                reportsToValue, // responsable
                resultSet.getString("jobTitle")
        );
    }
}

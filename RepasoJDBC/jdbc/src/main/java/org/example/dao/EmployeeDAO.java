package org.example.dao;

import org.example.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
 * DAO significa Data Access Object. Esta interfaz define las operaciones
 * permitidas sobre employees sin mezclar SQL con el resto de la aplicación.
 *
 * Las clases y las interfaces usan UpperCamelCase: cada palabra empieza por
 * mayúscula, por ejemplo EmployeeDAO. Los métodos, variables y parámetros
 * usan lowerCamelCase: la primera palabra empieza en minúscula y las demás
 * con mayúscula, por ejemplo findByOfficeCode. Es la convención habitual
 * de nombres de Java.
 */
public interface EmployeeDAO {

    /*
     * Las operaciones JDBC pueden fallar por problemas de red, credenciales,
     * SQL o disponibilidad del servidor. SQLException es la excepción
     * comprobada que representa esos errores; throws obliga al código que
     * utilice el DAO a tratarlos o declararlos.
     */
    List<Employee> findAll() throws SQLException;

    /*
     * Optional<Employee> expresa que la búsqueda puede devolver un Employee
     * o no devolver ninguno. Es una forma explícita de representar la ausencia
     * de un resultado y evita devolver directamente null.
     *
     * El código que llame a este método debe decidir qué hacer en ambos casos,
     * por ejemplo usando ifPresent(), isPresent() o un valor alternativo.
     */
    Optional<Employee> findById(int employeeNumber) throws SQLException;

    List<Employee> findByOfficeCode(String officeCode) throws SQLException;

    /*
     * save() inserta un nuevo empleado y devuelve true si se ha insertado
     * exactamente una fila.
     */
    boolean save(Employee employee) throws SQLException;
}

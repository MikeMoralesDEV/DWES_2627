package org.example.dao;

import org.example.Conexion;
import org.example.model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDAOTest {

    private static EmployeeDAO employeeDAO;

    @BeforeAll
    static void iniciar() {
        employeeDAO = new EmployeeDAOImpl();
    }

    @AfterAll
    static void cerrar() throws SQLException {
        Conexion.getInstancia().cerrar();
    }

    @Test
    void buscaUnEmpleadoExistente() throws SQLException {
        assertTrue(employeeDAO.findById(1002).isPresent());
    }

    @Test
    void devuelveVacioSiNoEncuentraEmpleado() throws SQLException {
        assertFalse(employeeDAO.findById(-1).isPresent());
    }

    @Test
    void guardaUnEmpleado() throws SQLException {
        Employee employee = new Employee(
                9999,
                "Test",
                "Employee",
                "x9999",
                "test@example.com",
                "1",
                null,
                "Test Employee"
        );

        assertTrue(employeeDAO.save(employee));
    }
}

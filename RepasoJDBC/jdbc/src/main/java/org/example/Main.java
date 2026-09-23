package org.example;

import java.sql.SQLException;
import java.util.Scanner;

import org.example.dao.EmployeeDAO;
import org.example.dao.EmployeeDAOImpl;
import org.example.model.Employee;

public class Main {

    public static void main(String[] args) {
        Conexion conexionSingleton = Conexion.getInstancia();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Todos los empleados:");
            for (Employee employee : employeeDAO.findAll()) {
                System.out.println(employee);
            }

            System.out.println("\nEmpleado con número 1002:");
            employeeDAO.findById(1002).ifPresent(System.out::println);

            System.out.println("\nEmpleados de la oficina 1:");
            for (Employee employee : employeeDAO.findByOfficeCode("1")) {
                System.out.println(employee);
            }

            System.out.println("\nIntroducir un nuevo empleado");
            Employee employee = leerEmpleado(scanner);

            if (employeeDAO.save(employee)) {
                System.out.println("Empleado guardado correctamente");
            } else {
                System.out.println("No se ha guardado el empleado");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar employees: "
                    + e.getMessage());
            /*
             * En aplicaciones profesionales veremos sistemas de logging,
             * como java.util.logging, SLF4J o Log4j. Permiten registrar
             * errores con niveles, fechas y destinos configurables, en lugar
             * de escribir directamente en la consola.
             */
        } finally {
            try {
                conexionSingleton.cerrar();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: "
                        + e.getMessage());
            }
        }
    }

    private static Employee leerEmpleado(Scanner scanner) {
        System.out.print("Número de empleado: ");
        int employeeNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();

        System.out.print("Nombre: ");
        String firstName = scanner.nextLine();

        System.out.print("Extensión: ");
        String extension = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Código de oficina: ");
        String officeCode = scanner.nextLine();

        System.out.print("Número del responsable (Intro si no tiene): ");
        String reportsToText = scanner.nextLine();
        Integer reportsTo = reportsToText.isBlank()
                ? null
                : Integer.parseInt(reportsToText);

        System.out.print("Puesto: ");
        String jobTitle = scanner.nextLine();

        return new Employee(
                employeeNumber,
                lastName,
                firstName,
                extension,
                email,
                officeCode,
                reportsTo,
                jobTitle
        );
    }
}

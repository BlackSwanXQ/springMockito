package com.example.springMockito.services;

import com.example.springMockito.employee.Employee;
import com.example.springMockito.exceptions.EmployeeAlreadyAddedException;
import com.example.springMockito.exceptions.EmployeeNotFoundException;
import com.example.springMockito.exceptions.EmployeeStorageIsFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServicesImplTest {

    private EmployeeServices employeeServices;


    @BeforeEach
    void setEmployeeServices() {
        employeeServices = new EmployeeServicesImpl();
    }

    @Test
    void addEmployeeTest() {
        Employee actual = employeeServices.add("Ivan", "Ivanov", 1000, 2);
        Employee expected = new Employee("Ivan", "Ivanov", 1000, 2);
        assertEquals(expected, actual);
    }

    @Test
    void addEmployeeAlreadyExistTest() {
        assertThrows(EmployeeAlreadyAddedException.class, () -> {
                    employeeServices.add("Ivan", "Ivanov", 1000, 2);
                    employeeServices.add("Ivan", "Ivanov", 1000, 2);
                }
        );
    }

    @Test
    void addEmployeeStorageFullTest() {
        assertThrows(EmployeeStorageIsFullException.class, () -> {
            employeeServices.add("Avan", "Ivanov", 1000, 2);
            employeeServices.add("Bvan", "Ivanov", 1000, 2);
            employeeServices.add("Cvan", "Ivanov", 1000, 2);
            employeeServices.add("Dvan", "Ivanov", 1000, 2);
            employeeServices.add("Evan", "Ivanov", 1000, 2);
            employeeServices.add("Fvan", "Ivanov", 1000, 2);
        });
    }

    @Test
    void removeEmployeeTest() {
        Employee expected = employeeServices.add("Ivan", "Ivanov", 1000, 2);
        Employee actual = employeeServices.remove("Ivan", "Ivanov");
        assertEquals(expected, actual);
    }

    @Test
    void removeEmployeeNotFoundTest() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServices.add("Ivan", "Ivanov", 1000, 2);
            employeeServices.remove("Ivannnnn", "Ivanov");
        });
    }

    @Test
    void findEmployeeTest() {
        Employee expected = employeeServices.add("Ivan", "Ivanov", 1000, 2);
        Employee actual = employeeServices.find("Ivan", "Ivanov");
        assertEquals(expected, actual);
    }

    @Test
    void findEmployeeNotFoundTest() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServices.add("Ivan", "Ivanov", 1000, 2);
            employeeServices.remove("Ivannnn", "Ivanov");
        });
    }

    @Test
    void getAllEmployeesTest() {
        Collection<Employee> expected = new ArrayList<>(List.of(
                employeeServices.add("Avan", "Ivanov", 1000, 2),
                employeeServices.add("Bvan", "Ivanov", 1000, 2),
                employeeServices.add("Cvan", "Ivanov", 1000, 2)
        ));
        Collection<Employee> actual = employeeServices.get();
        assertEquals(expected.containsAll(actual), true);
    }


}
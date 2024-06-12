package com.example.springMockito.services;

import com.example.springMockito.employee.Employee;
import com.example.springMockito.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServicesImplTest {

    @InjectMocks
    private DepartmentServicesImpl out;

    @Mock
    private EmployeeServicesImpl employeeServices;


    @Test
    void minSalaryWithDeparnment() {

        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertEquals(employee2, out.minSalary(2));
    }

    @Test
    void minSalaryThrowException() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);
        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertThrows(EmployeeNotFoundException.class, () -> out.minSalary(3));
    }


    @Test
    void maxSalaryWithDeparnment() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertEquals(employee1, out.maxSalary(2));
    }


    @Test
    void maxSalaryThrowException() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);
        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertThrows(EmployeeNotFoundException.class, () -> out.maxSalary(3));
    }

    @Test
    void employeeDepartment() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertEquals(employees, out.employeeDepartment(2));
    }

    @Test
    void  allEmployeesDepartmentNotSuchElement() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        Collection<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        List<Employee> emptyList = new ArrayList<>();
        assertEquals(emptyList, out.employeeDepartment(4));
    }

    @Test
    void sumDepartmentSalary() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertEquals(1100, out.sumDepartmentSalary(2));

    }

    @Test
    void employeeSumSalaryDepartmentNotSuchElement() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeServices.getAll())
                .thenReturn(employees);
        assertEquals(0,out.sumDepartmentSalary(4));
    }

    @Test
    void allEmployee() {
        Employee employee1 = new Employee("Avan", "Ivanov", 1000, 2);
        Employee employee2 = new Employee("Bvan", "Ivanov", 100, 2);
        Employee employee3 = new Employee("Cvan", "Ivanov", 1004, 3);
        Employee employee4 = new Employee("Dvan", "Ivanov", 100, 3);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        List<Employee> employees2 = new ArrayList<>(List.of(
                employee3,
                employee4));

        List<Employee> union = new ArrayList<>();
        union.addAll(employees);
        union.addAll(employees2);

        Map<Integer, List<Employee>> expected = new HashMap<>();
        expected.put(2, employees);
        expected.put(3, employees2);

        when(employeeServices.getAll())
                .thenReturn(union);
        Map<Integer, List<Employee>> actual = out.allEmployee();
        assertEquals(expected, actual);
    }

  }
package com.example.springMockito.services;

import com.example.springMockito.employee.Employee;
import com.example.springMockito.exceptions.EmployeeAlreadyAddedException;
import com.example.springMockito.exceptions.EmployeeNotFoundException;
import com.example.springMockito.exceptions.EmployeeStorageIsFullException;
import com.example.springMockito.exceptions.InvalidInputException;
import com.example.springMockito.services.EmployeeServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
    private final Map<String, Employee> employees;
    private final int employeesMax;

    public EmployeeServicesImpl() {
        this.employees = new HashMap<>();
        this.employeesMax = 5;
    }

    @Override
    public String greet() {
        return "<h1> Hello worker! </h1>";
    }

    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        validateInput(firstName, lastName);
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Employee already exist");
        }
        if (employees.size() >= employeesMax) {
            throw new EmployeeStorageIsFullException("List is overflowing");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName, lastName);
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateInput(firstName, lastName);
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public Collection<Employee> get() {
        return Collections.unmodifiableCollection(employees.values());
    }


    private String buildKey(String firstname, String lastname) {
        return firstname + lastname;
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    private void validateInput(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InvalidInputException("Incorrect Employee");
        }
    }
}


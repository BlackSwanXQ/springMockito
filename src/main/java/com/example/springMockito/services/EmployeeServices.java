package com.example.springMockito.services;

import com.example.springMockito.employee.Employee;

import java.util.Collection;

public interface EmployeeServices {
    String greet();

    Employee add(String firstName, String lastName, Integer salary, Integer department);

    Employee remove(String fistName, String lastName);

    Employee find(String firstName, String lastName);


    Collection<Employee> get();

    Collection<Employee> getAll();
}

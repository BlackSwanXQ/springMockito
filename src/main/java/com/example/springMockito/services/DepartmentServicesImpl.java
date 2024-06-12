package com.example.springMockito.services;

import com.example.springMockito.employee.Employee;
import com.example.springMockito.exceptions.EmployeeNotFoundException;
import com.example.springMockito.services.DepartmentServices;
import com.example.springMockito.services.EmployeeServices;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServicesImpl implements DepartmentServices {
    private final EmployeeServices employeeServices;

    public DepartmentServicesImpl(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @Override
    public Employee minSalary(int department) {
        return employeeServices.getAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee maxSalary(int department) {
        return employeeServices.getAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> employeeDepartment(int department) {
        return employeeServices.getAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Integer sumDepartmentSalary(int department) {
        return employeeServices.getAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(e -> e.getSalary()).sum();
    }

    @Override
    public Map<Integer, List<Employee>> allEmployee() {
        return employeeServices.getAll()
                .stream()
                .collect(groupingBy(Employee::getDepartment));
    }

}

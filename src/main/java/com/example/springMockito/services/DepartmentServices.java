package com.example.springMockito.services;
import com.example.springMockito.employee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentServices {
    Employee minSalary(int department);

    Employee maxSalary(int department);

    Collection<Employee> employeeDepartment(int department);

    Map<Integer, List<Employee>> allEmployee();
    Integer sumDepartmentSalary(int department);

}

package com.example.springMockito.controllers;

import com.example.springMockito.employee.Employee;
import com.example.springMockito.services.DepartmentServices;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee/departments")
public class DepartmentController {
    private final DepartmentServices departmentService;

    public DepartmentController(DepartmentServices departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{department}/salary/min")
    public Employee findDepartmentSalaryMin(@PathVariable int department) {
        return departmentService.minSalary(department);
    }

    @GetMapping("/{department}/salary/max")
    public Employee findDepartmentSalaryMax(@PathVariable int department) {
        return departmentService.maxSalary(department);
    }

    @GetMapping(value = "/all/{department}/employees")
    public Collection<Employee> allEmployeeDepartment(@PathVariable Integer department) {
        return departmentService.employeeDepartment(department);
    }

    @GetMapping("/{department}/salary/sum")
    public Integer sumDepartmentSalary(@PathVariable Integer department) {
        return  departmentService.sumDepartmentSalary(department);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> allEmployee() {
        return departmentService.allEmployee();
    }
}

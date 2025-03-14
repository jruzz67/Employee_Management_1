package com.examly.springapp.service;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Map<String, List<Employee>> groupEmployeesByAttribute(String attribute) {
        List<Employee> employees = employeeRepo.findAll();
        if (attribute.equals("designation")) {
            return employees.stream().collect(Collectors.groupingBy(Employee::getDesignation));
        }
        return Collections.emptyMap();
    }

    public List<Employee> findEmployeesByAttribute(String attribute, String value) {
        if (attribute.equals("name")) {
            return employeeRepo.findByName(value);
        }
        if (attribute.equals("designation")) {
            return employeeRepo.findByDesignation(value);
        }
        return Collections.emptyList();
    }

    public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepo.findAll().stream()
                .filter(emp -> emp.getSalary() >= minSalary && emp.getSalary() <= maxSalary)
                .collect(Collectors.toList());
    }
}

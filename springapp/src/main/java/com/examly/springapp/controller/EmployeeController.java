package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return (employee != null) ? new ResponseEntity<>(employee, HttpStatus.OK)
                                  : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/groupBy/{attribute}")
    public ResponseEntity<Map<String, List<Employee>>> getEmployeesGroupedByAttribute(@PathVariable String attribute) {
        try {
            Map<String, List<Employee>> groupedEmployees = employeeService.groupEmployeesByAttribute(attribute);
            return new ResponseEntity<>(groupedEmployees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBy/{attribute}")
    public ResponseEntity<List<Employee>> findEmployeesByAttribute(@PathVariable String attribute,
                                                                   @RequestParam("value") String value) {
        try {
            List<Employee> employees = employeeService.findEmployeesByAttribute(attribute, value);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/salaryRange")
    public ResponseEntity<List<Employee>> findEmployeesBySalaryRange(@RequestParam double minSalary,
                                                                     @RequestParam double maxSalary) {
        try {
            List<Employee> employees = employeeService.findEmployeesBySalaryRange(minSalary, maxSalary);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

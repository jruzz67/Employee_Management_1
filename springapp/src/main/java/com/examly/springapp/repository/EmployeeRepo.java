package com.examly.springapp.repository;

import com.examly.springapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    List<Employee> findByDesignation(String designation);
}

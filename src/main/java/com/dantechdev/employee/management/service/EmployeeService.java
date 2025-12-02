package com.dantechdev.employee.management.service;

import com.dantechdev.employee.management.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
    Employee update(Employee employee);
}

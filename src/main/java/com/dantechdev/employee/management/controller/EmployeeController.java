package com.dantechdev.employee.management.controller;

import com.dantechdev.employee.management.model.Employee;
import com.dantechdev.employee.management.model.Role;
import com.dantechdev.employee.management.service.EmployeeService;
import com.dantechdev.employee.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);

        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Set<Role> rolesList = new HashSet<>();
        Role readRole;

        for(Role role : employee.getRolesList()) {
            readRole = roleService.findById(role.getId()).orElse(null);
            if(readRole != null) {
                rolesList.add(readRole);
            }
        }
        if(!rolesList.isEmpty()){
            employee.setRolesList(rolesList);

            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.findById(id).orElse(null);
        if(updatedEmployee != null) {
            updatedEmployee = employee;

            return ResponseEntity.ok(employeeService.save(updatedEmployee));
        }
        return ResponseEntity.notFound().build();
    }
}
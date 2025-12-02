package com.dantechdev.employee.management.controller;

import com.dantechdev.employee.management.model.Permission;
import com.dantechdev.employee.management.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAll() {
        List<Permission> permissions = permissionService.findAll();

        return ResponseEntity.ok(permissions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getById(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.findById(id);

        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Permission> save(@RequestBody Permission permission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.save(permission));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        permissionService.deleteById(id);
    }
    @PutMapping
    public ResponseEntity<Permission> update(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.save(permission));
    }
}

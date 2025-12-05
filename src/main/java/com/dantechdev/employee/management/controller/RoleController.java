package com.dantechdev.employee.management.controller;


import com.dantechdev.employee.management.model.Permission;
import com.dantechdev.employee.management.model.Role;
import com.dantechdev.employee.management.service.PermissionService;
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
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Set<Permission> permissionsList = new HashSet<>();
        Permission readPermission;

        for(Permission permission : role.getPermissionsList()) {
            readPermission = permissionService.findById(permission.getId()).orElse(null);

            if(readPermission != null) {
                permissionsList.add(readPermission);
            }
        }
        if(!permissionsList.isEmpty()) {
            role.setPermissionsList(permissionsList);

            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.findById(id).orElse(null);

        if(updatedRole != null) {
            updatedRole = role;
        }
        roleService.update(updatedRole);

        return ResponseEntity.ok(updatedRole);
    }
}

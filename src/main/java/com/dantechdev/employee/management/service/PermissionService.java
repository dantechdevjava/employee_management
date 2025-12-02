package com.dantechdev.employee.management.service;

import com.dantechdev.employee.management.model.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<Permission> findAll();
    Optional<Permission> findById(Long id);
    Permission save(Permission permission);
    void delete(Permission permission);
    Permission update(Permission permission);
}

package com.dantechdev.employee.management.service;

import com.dantechdev.employee.management.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    Role update(Long id, Role role);
}

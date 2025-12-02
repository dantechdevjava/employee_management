package com.dantechdev.employee.management.repository;

import com.dantechdev.employee.management.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}

package com.dantechdev.employee.management.repository;

import com.dantechdev.employee.management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

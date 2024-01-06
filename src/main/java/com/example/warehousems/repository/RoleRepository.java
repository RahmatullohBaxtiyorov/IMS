package com.example.warehousems.repository;

import com.example.warehousems.entity.Role;
import com.example.warehousems.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}

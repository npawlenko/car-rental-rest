package com.example.carrental.repository;

import com.example.carrental.domain.Role;
import com.example.carrental.domain.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleEnum name);
}

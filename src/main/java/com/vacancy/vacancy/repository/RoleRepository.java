package com.vacancy.vacancy.repository;

import com.vacancy.vacancy.entity.Role;
import com.vacancy.vacancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

}

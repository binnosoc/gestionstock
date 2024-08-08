package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByName(String roleStudent);
}

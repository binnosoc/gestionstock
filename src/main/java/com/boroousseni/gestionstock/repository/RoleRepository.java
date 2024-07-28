package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findById(Integer id);
	Optional<Role> findByName(String name);
	List<Role> findByUserId(Integer id);
}

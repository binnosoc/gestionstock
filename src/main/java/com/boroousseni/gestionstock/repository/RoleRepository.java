package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

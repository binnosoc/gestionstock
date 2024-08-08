package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String username);
}

package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boroousseni.gestionstock.models.User;

public interface UserRepository  extends JpaRepository<User, Integer> {

	  // JPQL query
	  @Query(value = "select u from User u where u.email = :email")
	  Optional<User> findUserByEmail(@Param("email") String email);
}

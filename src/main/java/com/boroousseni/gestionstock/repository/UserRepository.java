package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boroousseni.gestionstock.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select u from User u where u.baseInfo.email = :email")
	Optional<User> findByBaseInfoEmail(String email);

	List<User> findAllByCompanyId(Integer id);
}

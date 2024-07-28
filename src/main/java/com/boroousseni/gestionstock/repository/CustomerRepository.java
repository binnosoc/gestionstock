package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findById(Integer customerId);
	Optional<Customer> findByBaseInfoName(String name);
	Optional<Customer> findByBaseInfoEmail(String email);
	Optional<Customer> findByBaseInfoPhone(String email);
	List<Customer> findAllByCompanyId(Integer id);

}

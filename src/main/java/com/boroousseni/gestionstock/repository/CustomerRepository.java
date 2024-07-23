package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findCustomerByCustomerId(Integer customerId);
	Optional<Customer> findCustomerByName(String name);
	Optional<Customer> findCustomerByEmail(String email);

}

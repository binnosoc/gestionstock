package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	List<CustomerOrder> findAllByCustomerId(Integer customerID);
	List<CustomerOrder> findAllByCustomerStatus(String status);
	Optional<CustomerOrder> findCustomerOrderById(Integer customerOrderID);
}

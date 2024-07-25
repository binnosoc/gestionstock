package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.models.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	
	List<CustomerOrder> findAllByCustomerId(Integer customerID);

	List<CustomerOrderLigneDto> findAllCustomerOrderLigneByCustomerOrderId(Integer orderID);

	Optional<CustomerOrder> findCustomerOrderById(Integer customerOrderID);
}

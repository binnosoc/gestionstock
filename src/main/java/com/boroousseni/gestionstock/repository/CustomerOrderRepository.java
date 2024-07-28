package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.models.CustomerOrder;
import com.boroousseni.gestionstock.models.CustomerOrderLigne;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {	
	List<CustomerOrder> findAllByCustomerId(Integer customerID);	
	List<CustomerOrder> findAllByCompanyId(Integer customerID);
	List<CustomerOrder> findAllByStatus(String status);
	Optional<CustomerOrder> findById(Integer customerOrderID);
}

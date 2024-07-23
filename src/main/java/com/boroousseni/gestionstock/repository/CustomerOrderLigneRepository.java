package com.boroousseni.gestionstock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.CustomerOrderLigne;

public interface CustomerOrderLigneRepository extends JpaRepository<CustomerOrderLigne, Integer> {
	List<CustomerOrderLigne> findAllByCustomerOrderId(Integer customerOrderID);
	List<CustomerOrderLigne> findAllByItemId(Integer itemID);	
}

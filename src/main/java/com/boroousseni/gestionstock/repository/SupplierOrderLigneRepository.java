package com.boroousseni.gestionstock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SupplierOrderLigne;

public interface SupplierOrderLigneRepository extends JpaRepository<SupplierOrderLigne, Integer> {
	
	List<SupplierOrderLigne> findAllBySupplierOrderId(Integer orderID); 

	List<SupplierOrderLigne> findAllByArticleId(Integer orderID);
}

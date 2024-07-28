package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SupplierOrderLigne;

public interface SupplierOrderLigneRepository extends JpaRepository<SupplierOrderLigne, Integer> {
	
	Optional<SupplierOrderLigne> findById(Integer id);
	
	List<SupplierOrderLigne> findAllBySupplierOrderId(Integer id); 

	List<SupplierOrderLigne> findAllByItemId(Integer id);
}

package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SupplierOrder;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Integer> {
	

	Optional<SupplierOrder> findById(Integer id);

	List<SupplierOrder> findAllBySupplierId(Integer id);
}

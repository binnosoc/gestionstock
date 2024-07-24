package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SupplierOrder;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Integer> {

}

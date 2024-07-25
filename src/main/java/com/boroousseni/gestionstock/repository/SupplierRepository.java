package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}

package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SupplierOrderLigne;

public interface SupplierOrderLigneRepository  extends JpaRepository<SupplierOrderLigne, Integer> {

}

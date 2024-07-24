package com.boroousseni.gestionstock.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Sale;

public interface SaleRepository  extends JpaRepository<Sale, Integer> {
	Optional<Sale> findSaleByCode(String code);
}

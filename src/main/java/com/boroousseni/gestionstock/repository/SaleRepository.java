package com.boroousseni.gestionstock.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Sale;

public interface SaleRepository  extends JpaRepository<Sale, Integer> {
	Optional<Sale> findByCode(String code);
	Optional<Sale> findById(Integer id);
	List<Sale> findAllByCompanyId(Integer id);
}

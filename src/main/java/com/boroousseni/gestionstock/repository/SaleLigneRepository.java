package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.SaleLigne;

public interface SaleLigneRepository extends JpaRepository<SaleLigne, Integer> {
	Optional<SaleLigne> findById(Integer id);
	List<SaleLigne> findAllBySaleId(Integer id);
	List<SaleLigne> findAllByItemId(Integer id);
}

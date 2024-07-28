package com.boroousseni.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	Optional<Supplier> findById(Integer id);
	Optional<Supplier> findByBaseInfoName(String name);
	Optional<Supplier> findByBaseInfoEmail(String email);
	Optional<Supplier> findByBaseInfoPhone(String email);
	List<Supplier> findAllByCompanyId(Integer id);
}

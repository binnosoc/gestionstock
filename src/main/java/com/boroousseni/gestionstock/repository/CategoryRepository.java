package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer>	 {
	Optional<Category> findCategoryByName(String name);
	
}

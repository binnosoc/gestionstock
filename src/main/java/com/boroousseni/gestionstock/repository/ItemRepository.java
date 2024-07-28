package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Item;
import java.util.List;
import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	Optional<Item> findByCode(String itemCode);
	Optional<Item> findById(Integer itemID);
	Optional<Item> findByName(String name);
	List<Item> findAllByCategoryId(Integer id);
	List<Item> findAllByCompanyId(Integer id);
}

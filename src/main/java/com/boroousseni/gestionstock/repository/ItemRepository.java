package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Item;
import java.util.List;
import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	Optional<Item> findItemByItemCode(String itemCode);
	Optional<Item> findItemByItemId(Integer itemID);
	Optional<Item> findItemByName(String name);
	List<Item> findAllByCategoryId(Integer categoryID);
}

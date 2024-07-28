package com.boroousseni.gestionstock.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boroousseni.gestionstock.models.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
	
	@Query("select sum(m.quantity) from StockMovement m where m.item.id = :itemID")
	BigDecimal stockReelItem(@Param("itemID") Integer itemID);

	List<StockMovement> findAllByItemId(Integer id);
	List<StockMovement> findAllByCompanyId(Integer id);
}

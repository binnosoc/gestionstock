package com.boroousseni.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.StockMovement;

public interface StockMovementRepository  extends JpaRepository<StockMovement, Integer> {

}

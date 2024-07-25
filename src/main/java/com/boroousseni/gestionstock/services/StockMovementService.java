package com.boroousseni.gestionstock.services;

import java.math.BigDecimal;
import java.util.List;

import com.boroousseni.gestionstock.dto.StockMovementDto;

public interface StockMovementService {
	
	BigDecimal stockReelItem(Integer itemID);

	List<StockMovementDto> stockMovementItem(Integer itemID);

	StockMovementDto entreeStock(StockMovementDto dto);

	StockMovementDto sortieStock(StockMovementDto dto);

	StockMovementDto correctionStockPos(StockMovementDto dto);

	StockMovementDto correctionStockNeg(StockMovementDto dto);
}

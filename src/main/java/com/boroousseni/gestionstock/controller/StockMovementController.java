package com.boroousseni.gestionstock.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.StockMovementApi;
import com.boroousseni.gestionstock.dto.StockMovementDto;
import com.boroousseni.gestionstock.services.StockMovementService;

@RestController
public class StockMovementController implements StockMovementApi {

	private StockMovementService stockMovementService;

	@Autowired
	public StockMovementController(StockMovementService stockMovementService) {
		this.stockMovementService = stockMovementService;
	}
	@Override
	public BigDecimal stockReelItem(Integer itemID) {
		// TODO Auto-generated method stub
		return stockMovementService.stockReelItem(itemID);
	}

	@Override
	public List<StockMovementDto> stockMovementItem(Integer itemID) {
		// TODO Auto-generated method stub
		return stockMovementService.stockMovementItem(itemID);
	}

	@Override
	public StockMovementDto entreeStock(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return stockMovementService.entreeStock(dto);
	}

	@Override
	public StockMovementDto sortieStock(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return stockMovementService.sortieStock(dto);
	}

	@Override
	public StockMovementDto correctionStockPos(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return stockMovementService.correctionStockNeg(dto);
	}

	@Override
	public StockMovementDto correctionStockNeg(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return stockMovementService.correctionStockNeg(dto);
	}

}

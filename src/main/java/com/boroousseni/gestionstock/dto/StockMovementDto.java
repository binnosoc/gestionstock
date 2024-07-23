package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.StockMovement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockMovementDto {
	private Integer stockMovementId;
	private ItemDto item;
	
	public static StockMovementDto fromEntity(StockMovement stockMovement) {
		if(stockMovement==null) {
			return null;
		}
		
		return StockMovementDto.builder()
				.stockMovementId(stockMovement.getStockMovementId())
				.item(ItemDto.fromEntity(stockMovement.getItem()))
				.build();
	}
	
	public static StockMovement toEntity(StockMovementDto stockMovementDto) {
		
		if(stockMovementDto==null) {
			return null;
		}
		
		StockMovement stockMovement = new StockMovement();
		stockMovement.setStockMovementId(stockMovementDto.getStockMovementId());
		stockMovement.setItem(ItemDto.toEntity(stockMovementDto.getItem()));
		
		return stockMovement;
	}
}

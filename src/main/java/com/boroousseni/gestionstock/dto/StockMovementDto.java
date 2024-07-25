package com.boroousseni.gestionstock.dto;

import java.time.Instant;

import com.boroousseni.gestionstock.models.Company;
import com.boroousseni.gestionstock.models.StockMovement;
import com.boroousseni.gestionstock.models.StockSource;
import com.boroousseni.gestionstock.models.TypeOfStock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockMovementDto {
	private Integer stockMovementId;
	private ItemDto item;
	private Instant stockMovementDate;
	private Integer quantity;
	private TypeOfStock typeOfStock;
	private StockSource stockSource;
	private Company company;
	
	public static StockMovementDto fromEntity(StockMovement stockMovement) {
		if(stockMovement==null) {
			return null;
		}
		
		return StockMovementDto.builder()
				.stockMovementId(stockMovement.getStockMovementId())
				.item(ItemDto.fromEntity(stockMovement.getItem()))
				.stockMovementDate(stockMovement.getStockMovementDate())
				.quantity(stockMovement.getQuantity())
				.typeOfStock(stockMovement.getTypeOfStock())
				.stockSource(stockMovement.getStockSource())				
				.build();
	}
	
	public static StockMovement toEntity(StockMovementDto stockMovementDto) {
		
		if(stockMovementDto==null) {
			return null;
		}
		
		StockMovement stockMovement = new StockMovement();
		stockMovement.setStockMovementId(stockMovementDto.getStockMovementId());		
		stockMovement.setItem(ItemDto.toEntity(stockMovementDto.getItem()));
		stockMovement.setStockMovementDate(stockMovementDto.getStockMovementDate());
		stockMovement.setQuantity(stockMovementDto.getQuantity());
		stockMovement.setTypeOfStock(stockMovementDto.getTypeOfStock());
		stockMovement.setStockSource(stockMovementDto.getStockSource());		
		
		return stockMovement;
	}
}

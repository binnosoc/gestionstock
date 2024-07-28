package com.boroousseni.gestionstock.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.StockMovementDto;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.models.TypeOfStock;
import com.boroousseni.gestionstock.repository.StockMovementRepository;
import com.boroousseni.gestionstock.services.ItemService;
import com.boroousseni.gestionstock.services.StockMovementService;
import com.boroousseni.gestionstock.validators.StockMovementValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockMovementServiceImpl implements StockMovementService {

	private StockMovementRepository stockMovementRepository;
	private ItemService itemService;

	@Autowired
	public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, ItemService itemService) {
		this.stockMovementRepository = stockMovementRepository;
		this.itemService = itemService;
	}

	@Override
	public BigDecimal stockReelItem(Integer itemID) {
		// TODO Auto-generated method stub
		if (itemID == null) {
			log.warn("ID item is NULL");
			return BigDecimal.valueOf(-1);
		}
		itemService.findById(itemID);
		return stockMovementRepository.stockReelItem(itemID);
	}

	@Override
	public List<StockMovementDto> stockMovementItem(Integer itemID) {
		// TODO Auto-generated method stub
		return stockMovementRepository.findAllByItemId(itemID).stream().map(StockMovementDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public StockMovementDto entreeStock(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return entreePositive(dto, TypeOfStock.ENTREE);
	}

	@Override
	public StockMovementDto sortieStock(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return sortieNegative(dto, TypeOfStock.SORTIE);
	}

	@Override
	public StockMovementDto correctionStockPos(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return entreePositive(dto, TypeOfStock.CORRECTION_POS);
	}

	@Override
	public StockMovementDto correctionStockNeg(StockMovementDto dto) {
		// TODO Auto-generated method stub
		return sortieNegative(dto, TypeOfStock.CORRECTION_NEG);
	}

	private StockMovementDto entreePositive(StockMovementDto dto, TypeOfStock typeOfStock) {
		List<String> errors = StockMovementValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Item is not valid {}", dto);
			throw new InvalidEntityException("Le mouvement du stock n'est pas valide",
					ErrorCode.STOCK_MOVEMENT_NOT_VALID, errors);
		}

		dto.setTypeOfStock(typeOfStock);
		return StockMovementDto.fromEntity(stockMovementRepository.save(StockMovementDto.toEntity(dto)));
	}

	private StockMovementDto sortieNegative(StockMovementDto dto, TypeOfStock typeOfStock) {
		List<String> errors = StockMovementValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Item is not valid {}", dto);
			throw new InvalidEntityException("Le mouvement du stock n'est pas valide",
					ErrorCode.STOCK_MOVEMENT_NOT_VALID, errors);
		}

		dto.setTypeOfStock(typeOfStock);
		return StockMovementDto.fromEntity(stockMovementRepository.save(StockMovementDto.toEntity(dto)));
	}

}

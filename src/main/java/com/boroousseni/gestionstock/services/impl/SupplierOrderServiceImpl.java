package com.boroousseni.gestionstock.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.StockMovementDto;
import com.boroousseni.gestionstock.dto.SupplierDto;
import com.boroousseni.gestionstock.dto.SupplierOrderDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;
import com.boroousseni.gestionstock.models.Item;
import com.boroousseni.gestionstock.models.OrderStatus;
import com.boroousseni.gestionstock.models.StockSource;
import com.boroousseni.gestionstock.models.Supplier;
import com.boroousseni.gestionstock.models.SupplierOrder;
import com.boroousseni.gestionstock.models.SupplierOrderLigne;
import com.boroousseni.gestionstock.models.TypeOfStock;
import com.boroousseni.gestionstock.repository.ItemRepository;
import com.boroousseni.gestionstock.repository.SupplierOrderLigneRepository;
import com.boroousseni.gestionstock.repository.SupplierOrderRepository;
import com.boroousseni.gestionstock.repository.SupplierRepository;
import com.boroousseni.gestionstock.services.StockMovementService;
import com.boroousseni.gestionstock.services.SupplierOrderServcice;
import com.boroousseni.gestionstock.validators.ItemValidator;
import com.boroousseni.gestionstock.validators.SupplierOrderValidator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SupplierOrderServiceImpl implements SupplierOrderServcice {

	private SupplierOrderRepository supplierOrderRepository;
	private SupplierOrderLigneRepository supplierOrderLigneRepository;
	private SupplierRepository supplierRepository;
	private ItemRepository itemRepository;
	private StockMovementService stockMovementService;

	@Autowired
	public SupplierOrderServiceImpl(SupplierOrderRepository supplierOrderRepository,
			SupplierRepository supplierRepository, ItemRepository itemRepository,
			SupplierOrderLigneRepository supplierOrderLigneRepository, StockMovementService stockMovementService) {
		this.supplierOrderRepository = supplierOrderRepository;
		this.supplierOrderLigneRepository = supplierOrderLigneRepository;
		this.supplierRepository = supplierRepository;
		this.itemRepository = itemRepository;
		this.stockMovementService = stockMovementService;
	}

	@Override
	public SupplierOrderDto save(SupplierOrderDto dto) {
		// TODO Auto-generated method stub

		List<String> errors = SupplierOrderValidator.validate(dto);

		if (!errors.isEmpty()) {
			log.error("Order supplier n'est pas valide");
			throw new InvalidEntityException("La order supplier n'est pas valide", ErrorCode.SUPPLIER_ORDER_NOT_VALID,
					errors);
		}

		if (dto.getId() != null && dto.isDeliveredOrder()) {
			throw new InvalidOperationException("Impossible de modifier la order lorsqu'elle est livree",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}

		Optional<Supplier> supplier = supplierRepository.findById(dto.getSupplier().getId());
		if (supplier.isEmpty()) {
			log.warn("Supplier with ID {} was not found in the DB", dto.getSupplier().getId());
			throw new EntityNotFoundException(
					"Aucun supplier avec l'ID" + dto.getSupplier().getId() + " n'a ete trouve dans la BDD",
					ErrorCode.SUPPLIER_NOT_FOUND);
		}

		List<String> itemErrors = new ArrayList<>();

		if (dto.getSupplierOrdersLigne() != null) {
			dto.getSupplierOrdersLigne().forEach(ligCmdFrs -> {
				if (ligCmdFrs.getItem() != null) {
					Optional<Item> item = itemRepository.findById(ligCmdFrs.getItem().getId());
					if (item.isEmpty()) {
						itemErrors.add("article avec l'ID " + ligCmdFrs.getItem().getId() + " n'existe pas");
					}
				} else {
					itemErrors.add("Impossible d'enregister une order avec un aticle NULL");
				}
			});
		}

		if (!itemErrors.isEmpty()) {
			log.warn("");
			throw new InvalidEntityException("Item n'existe pas dans la BDD", ErrorCode.ITEM_NOT_FOUND, itemErrors);
		}
		dto.setOrderDate(Instant.now());
		SupplierOrder savedCmdFrs = supplierOrderRepository.save(SupplierOrderDto.toEntity(dto));

		if (dto.getSupplierOrdersLigne() != null) {
			dto.getSupplierOrdersLigne().forEach(ligCmdFrs -> {
				SupplierOrderLigne supplierOrderLigne = SupplierOrderLigneDto.toEntity(ligCmdFrs);
				supplierOrderLigne.setSupplierOrder(savedCmdFrs);
//	        supplierOrderLigne.setI(savedCmdFrs.getI);
				SupplierOrderLigne saveLigne = supplierOrderLigneRepository.save(supplierOrderLigne);

				doEntree(saveLigne);
			});
		}

		return SupplierOrderDto.fromEntity(savedCmdFrs);
	}

	@Override
	public SupplierOrderDto updateOrderStatus(Integer orderID, OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		if (StringUtils.isEmpty(String.valueOf(orderStatus))) {
			log.error("L'etat de la order supplier is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la order avec un etat null",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
		SupplierOrderDto supplierOrder = checkOrderStatus(orderID);
		supplierOrder.setStatus(orderStatus);

		SupplierOrder savedOrder = supplierOrderRepository.save(SupplierOrderDto.toEntity(supplierOrder));
		if (supplierOrder.isDeliveredOrder()) {
			updateMvtStk(orderID);
		}
		return SupplierOrderDto.fromEntity(savedOrder);
	}

	@Override
	public SupplierOrderDto updateSupplier(Integer orderID, Integer supplierID) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		if (supplierID == null) {
			log.error("L'ID du supplier is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la order avec un ID supplier null",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
		SupplierOrderDto supplierOrder = checkOrderStatus(orderID);
		Optional<Supplier> supplierOptional = supplierRepository.findById(supplierID);
		if (supplierOptional.isEmpty()) {
			throw new EntityNotFoundException("Aucun supplier n'a ete trouve avec l'ID " + supplierID,
					ErrorCode.SUPPLIER_NOT_FOUND);
		}
		supplierOrder.setSupplier(SupplierDto.fromEntity(supplierOptional.get()));

		return SupplierOrderDto.fromEntity(supplierOrderRepository.save(SupplierOrderDto.toEntity(supplierOrder)));
	}

	@Override
	public SupplierOrderDto updateItem(Integer orderID, Integer orderLigneId, Integer itemID) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneId);
		checkItemId(itemID, "nouvel");

		SupplierOrderDto supplierOrder = checkOrderStatus(orderID);

		Optional<SupplierOrderLigne> supplierOrderLigne = findSupplierOrderLigne(orderLigneId);

		Optional<Item> itemOptional = itemRepository.findById(itemID);
		if (itemOptional.isEmpty()) {
			throw new EntityNotFoundException("Aucune item n'a ete trouve avec l'ID " + itemID,
					ErrorCode.ITEM_NOT_FOUND);
		}

		List<String> errors = ItemValidator.validate(ItemDto.fromEntity(itemOptional.get()));
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Item invalid", ErrorCode.ITEM_NOT_VALID, errors);
		}

		SupplierOrderLigne supplierOrderLigneToSaved = supplierOrderLigne.get();
		supplierOrderLigneToSaved.setItem(itemOptional.get());
		supplierOrderLigneRepository.save(supplierOrderLigneToSaved);

		return supplierOrder;
	}

	@Override
	public SupplierOrderDto deleteItem(Integer orderID, Integer orderLigneID) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneID);

		SupplierOrderDto supplierOrder = checkOrderStatus(orderID);
		// Just to check the SupplierOrderLigne and inform the supplier in case it is
		// absent
		findSupplierOrderLigne(orderLigneID);
		supplierOrderLigneRepository.deleteById(orderLigneID);

		return supplierOrder;
	}

	@Override
	public SupplierOrderDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Order supplier ID is NULL");
			return null;
		}
		return supplierOrderRepository.findById(id).map(SupplierOrderDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("Aucune order supplier n'a ete trouve avec l'ID " + id,
						ErrorCode.SUPPLIER_ORDER_NOT_FOUND));
	}

//	@Override
//	public SupplierOrderDto findByCode(String code) {
//		// TODO Auto-generated method stub
//		if (StringUtils.isEmpty(code)) {
//			log.error("Order supplier CODE is NULL");
//			return null;
//		}
//		return supplierOrderRepository.findByCode(code).map(SupplierOrderDto::fromEntity).orElseThrow(
//				() -> new EntityNotFoundException("Aucune order supplier n'a ete trouve avec le CODE " + code,
//						ErrorCode.SUPPLIER_ORDER_NOT_FOUND));
//	}

	@Override
	public List<SupplierOrderDto> findAll() {
		// TODO Auto-generated method stub
		return supplierOrderRepository.findAll().stream().map(SupplierOrderDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<SupplierOrderLigneDto> findAllSupplierOrderLigneBySupplierOrderId(Integer orderID) {
		// TODO Auto-generated method stub
		return supplierOrderLigneRepository.findAllBySupplierOrderId(orderID).stream()
				.map(SupplierOrderLigneDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Order supplier ID is NULL");
			return;
		}
		List<SupplierOrderLigne> supplierOrderLignes = supplierOrderLigneRepository.findAllBySupplierOrderId(id);
		if (!supplierOrderLignes.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une order supplier deja utilisee",
					ErrorCode.SUPPLIER_ORDER_ALREADY_IN_USE);
		}
		supplierOrderRepository.deleteById(id);
	}

	private SupplierOrderDto checkOrderStatus(Integer orderID) {
		SupplierOrderDto supplierOrder = findById(orderID);
		if (supplierOrder.isDeliveredOrder()) {
			throw new InvalidOperationException("Impossible de modifier la order lorsqu'elle est livree",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
		return supplierOrder;
	}

	private Optional<SupplierOrderLigne> findSupplierOrderLigne(Integer orderLigneId) {
		Optional<SupplierOrderLigne> supplierOrderLigneOptional = supplierOrderLigneRepository.findById(orderLigneId);
		if (supplierOrderLigneOptional.isEmpty()) {
			throw new EntityNotFoundException("Aucune ligne order supplier n'a ete trouve avec l'ID " + orderLigneId,
					ErrorCode.SUPPLIER_ORDER_NOT_FOUND);
		}
		return supplierOrderLigneOptional;
	}

	private void checkOrderId(Integer orderID) {
		if (orderID == null) {
			log.error("Order supplier ID is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la order avec un ID null",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
	}

	private void checkOrderLigneId(Integer orderLigneId) {
		if (orderLigneId == null) {
			log.error("L'ID de la ligne order is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la order avec une ligne de order null",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
	}

	private void checkItemId(Integer itemID, String msg) {
		if (itemID == null) {
			log.error("L'ID de " + msg + " is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la order avec un " + msg + " ID item null",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}
	}

	private void updateMvtStk(Integer orderID) {
		List<SupplierOrderLigne> supplierOrderLigne = supplierOrderLigneRepository.findAllBySupplierOrderId(orderID);
		supplierOrderLigne.forEach(lig -> {
			doEntree(lig);
		});
	}

	private void doEntree(SupplierOrderLigne lig) {
		StockMovementDto stockMovementDto = StockMovementDto.builder().id(lig.getId())
				.item(ItemDto.fromEntity(lig.getItem())).stockMovementDate(Instant.now())
				.typeOfStock(TypeOfStock.ENTREE).stockSource(StockSource.COMMANDE_FOURNISSEUR).build();
		stockMovementService.entreeStock(stockMovementDto);
	}

	@Override
	public SupplierOrderDto updateOrderQuantity(Integer orderID, Integer orderLigneId, Integer quantity) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneId);

		if (quantity == null || quantity.compareTo(Integer.SIZE) == 0) {
			log.error("L'ID de la ligne commande is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la commande avec une quantity null ou ZERO",
					ErrorCode.SUPPLIER_ORDER_NOT_EDITABLE);
		}

		SupplierOrderDto commandeFournisseur = checkOrderStatus(orderID);
		Optional<SupplierOrderLigne> supplierOrderLigneOptional = findSupplierOrderLigne(orderLigneId);

		SupplierOrderLigne supplierOrderLigne = supplierOrderLigneOptional.get();

		supplierOrderLigneRepository.save(supplierOrderLigne);

		return commandeFournisseur;
	}

}

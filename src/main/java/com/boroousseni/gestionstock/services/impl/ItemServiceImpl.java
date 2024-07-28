package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.SaleLigneDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.repository.CustomerOrderLigneRepository;
import com.boroousseni.gestionstock.repository.ItemRepository;
import com.boroousseni.gestionstock.repository.SaleLigneRepository;
import com.boroousseni.gestionstock.repository.SupplierOrderLigneRepository;
import com.boroousseni.gestionstock.services.ItemService;
import com.boroousseni.gestionstock.validators.ItemValidator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private SaleLigneRepository saleLigneRepository;
	private SupplierOrderLigneRepository supplierOrderLigneRepository;
	private CustomerOrderLigneRepository customerOrderLigneRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, SaleLigneRepository saleLigneRepository,
			SupplierOrderLigneRepository supplierOrderLigneRepository,
			CustomerOrderLigneRepository customerOrderLigneRepository) {
		this.itemRepository = itemRepository;
		this.saleLigneRepository = saleLigneRepository;
		this.supplierOrderLigneRepository = supplierOrderLigneRepository;
		this.customerOrderLigneRepository = customerOrderLigneRepository;
	}

	@Override
	public ItemDto save(ItemDto dto) {
		// TODO Auto-generated method stub
		List<String> errors = ItemValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Item is not valid {}", dto);
			throw new InvalidEntityException("L'item n'est pas valide", ErrorCode.ITEM_NOT_VALID, errors);
		}

		return ItemDto.fromEntity(itemRepository.save(ItemDto.toEntity(dto)));
	}

	@Override
	public ItemDto findById(Integer itemID) {
		// TODO Auto-generated method stub
		if (itemID == null) {
			log.error("Item ID is null");
			return null;
		}

		return itemRepository.findById(itemID).map(ItemDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun item avec l'ID = " + itemID + " n' ete trouve dans la BDD", ErrorCode.ITEM_NOT_FOUND));
	}

	@Override
	public ItemDto findByCode(String itemCode) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(itemCode)) {
			log.error("Item CODE is null");
			return null;
		}

		return itemRepository.findByCode(itemCode).map(ItemDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun item avec le CODE = " + itemCode + " n' ete trouve dans la BDD",
						ErrorCode.ITEM_NOT_FOUND));
	}

	@Override
	public List<ItemDto> findAll() {
		// TODO Auto-generated method stub
		return itemRepository.findAll().stream().map(ItemDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<SaleLigneDto> findSaleHistory(Integer itemID) {
		// TODO Auto-generated method stub
		return saleLigneRepository.findAllByItemId(itemID).stream().map(SaleLigneDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerOrderLigneDto> findCustomerOrderLigneHistory(Integer itemID) {
		// TODO Auto-generated method stub
		return customerOrderLigneRepository.findAllByItemId(itemID).stream()
		        .map(CustomerOrderLigneDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public List<SupplierOrderLigneDto> findSupplierOrderLigneHistory(Integer itemID) {
		// TODO Auto-generated method stub
		return supplierOrderLigneRepository.findAllByItemId(itemID).stream()
		        .map(SupplierOrderLigneDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> findAllItemByCategoryId(Integer categoryID) {
		// TODO Auto-generated method stub
		return itemRepository.findAllByCategoryId(categoryID).stream()
		        .map(ItemDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer itemID) {
		// TODO Auto-generated method stub

		if (itemID == null) {
			log.error("Item ID is null");
			return;
		}

		itemRepository.deleteById(itemID);
	}

}

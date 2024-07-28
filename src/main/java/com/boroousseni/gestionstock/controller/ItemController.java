package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.ItemApi;
import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.SaleLigneDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.services.ItemService;


@RestController
public class ItemController implements ItemApi {

	private ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	
	public ItemDto save(ItemDto dto) {
		return itemService.save(dto);
	}

	@Override
	
	public ItemDto findById(Integer id) {
		return itemService.findById(id);
	}

	@Override
	public ItemDto findByCode(String codeItem) {
		return itemService.findByCode(codeItem);
	}

	@Override
	public List<ItemDto> findAll() {
		return itemService.findAll();
	}

	@Override
	public List<SaleLigneDto> findSaleHistory(Integer idItem) {
		return itemService.findSaleHistory(idItem);
	}

	@Override
	public List<CustomerOrderLigneDto> findCustomerOrderLigneHistory(Integer idItem) {
		return itemService.findCustomerOrderLigneHistory(idItem);
	}

	@Override
	public List<SupplierOrderLigneDto> findSupplierOrderLigneHistory(Integer idItem) {
		return itemService.findSupplierOrderLigneHistory(idItem);
	}

	@Override
	public List<ItemDto> findAllItemByCategoryId(Integer idCategory) {
		return itemService.findAllItemByCategoryId(idCategory);
	}

	@Override
	public void delete(Integer id) {
		itemService.delete(id);
	}

}

package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.SaleLigneDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;

public interface ItemService {

	  ItemDto save(ItemDto dto);

	  ItemDto findById(Integer itemID);

	  ItemDto findByCode(String itemCode);

	  List<ItemDto> findAll();

	  List<SaleLigneDto> findSaleHistory(Integer itemID);

	  List<CustomerOrderLigneDto> findCustomerOrderLigneHistory(Integer itemID);

	  List<SupplierOrderLigneDto> findSupplierOrderLigneHistory(Integer itemID);

	  List<ItemDto> findAllItemByCategoryId(Integer categoryID);

	  void delete(Integer itemID);

}

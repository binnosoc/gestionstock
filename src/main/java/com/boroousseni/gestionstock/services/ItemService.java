package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.SaleLigneDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;

public interface ItemService {

	  ItemDto save(ItemDto dto);

	  ItemDto findById(Integer itemID);

	  ItemDto findByItemCode(String itemCode);

	  List<ItemDto> findAll();

	  List<SaleLigneDto> findSaleLigneHistory(Integer itemID);

	  List<CustomerOrderLigneDto> findCustomerOrderLigneHistory(Integer itemID);

	  List<SupplierOrderLigneDto> findSupplierOrderLigneHistoryr(Integer itemID);

	  List<ItemDto> findAllItemByCategoryId(Integer categoryID);

	  void delete(Integer itemID);

}

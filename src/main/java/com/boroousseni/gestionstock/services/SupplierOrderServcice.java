package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.SupplierOrderDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.models.OrderStatus;

public interface SupplierOrderServcice {

	  SupplierOrderDto save(SupplierOrderDto dto);

	  SupplierOrderDto updateOrderStatus(Integer orderID, OrderStatus orderStatus);

	  SupplierOrderDto updateOrderQuantity(Integer orderID, Integer idLigneCommande, Integer quantity);

	  SupplierOrderDto updateSupplier(Integer orderID, Integer supplierID);

	  SupplierOrderDto updateItem(Integer orderID, Integer orderLigneID, Integer itemID);

	  // Delete article ==> delete LigneCommandeFournisseur
	  SupplierOrderDto deleteItem(Integer orderID, Integer orderLigneID);

	  SupplierOrderDto findById(Integer id);
	  

	  List<SupplierOrderDto> findAll();

	  List<SupplierOrderLigneDto> findAllSupplierOrderLigneBySupplierOrderId(Integer orderID);

	  void delete(Integer id);

}

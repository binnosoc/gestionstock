package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerOrderDto;
import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.models.OrderStatus;

public interface CustomerOrderServcie {

	  CustomerOrderDto save(CustomerOrderDto dto);

	  CustomerOrderDto updateOrderStatus(Integer orderID, OrderStatus orderStatus);

	  CustomerOrderDto updateOrderQuantity(Integer orderID, Integer orderLigneID, Integer quantity);

	  CustomerOrderDto updateCustomer(Integer orderID, Integer idClient);

	  CustomerOrderDto updateItem(Integer orderID, Integer orderLigneID, Integer ItemID);

	  CustomerOrderDto findById(Integer id);	  

	  List<CustomerOrderDto> findAllByCustomerId(Integer id);

	  void delete(Integer id);

}

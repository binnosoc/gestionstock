package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.CustomerOrderApi;
import com.boroousseni.gestionstock.dto.CustomerOrderDto;
import com.boroousseni.gestionstock.services.CustomerOrderServcie;

@RestController
public class CustomerOrderController implements CustomerOrderApi {
private CustomerOrderServcie customerOrderService;
	
	@Autowired
	public CustomerOrderController(CustomerOrderServcie customerOrderService) {
		this.customerOrderService=customerOrderService;
	}

	@Override
	public CustomerOrderDto save(CustomerOrderDto dto) {
		// TODO Auto-generated method stub
		return customerOrderService.save(dto) ;
	}

	@Override
	public CustomerOrderDto findById(Integer id) {
		// TODO Auto-generated method stub
		return customerOrderService.findById(id);
	}

	@Override
	public CustomerOrderDto updateOrderQuantity(Integer orderID, Integer orderLigneID, Integer quantity) {
		// TODO Auto-generated method stub
		return customerOrderService.updateOrderQuantity(orderID, orderLigneID, quantity);
	}

	@Override
	public CustomerOrderDto updateCustomer(Integer orderID, Integer idCustomer) {
		// TODO Auto-generated method stub
		return customerOrderService.updateCustomer(orderID, idCustomer);
	}

	@Override
	public CustomerOrderDto updateItem(Integer orderID, Integer idCustomer) {
		// TODO Auto-generated method stub
		return customerOrderService.updateItem(orderID, orderID, idCustomer);
	}

	@Override
	public List<CustomerOrderDto> findAllByCustomerOrderId(Integer id) {
		// TODO Auto-generated method stub
		return customerOrderService.findAllByCustomerId(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		customerOrderService.delete(id);
	}
}

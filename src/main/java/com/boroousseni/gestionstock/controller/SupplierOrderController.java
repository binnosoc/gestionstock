package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.SupplierOrderApi;
import com.boroousseni.gestionstock.dto.SupplierOrderDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.models.OrderStatus;
import com.boroousseni.gestionstock.services.SupplierOrderServcice;

@RestController
public class SupplierOrderController implements SupplierOrderApi {

	private SupplierOrderServcice supplierOrderService;

	@Autowired
	public SupplierOrderController(SupplierOrderServcice supplierOrderService) {
		this.supplierOrderService = supplierOrderService;
	}
	@Override
	public SupplierOrderDto save(SupplierOrderDto dto) {
		// TODO Auto-generated method stub
		return supplierOrderService.save(dto);
	}

	@Override
	public SupplierOrderDto updateOrderStatus(Integer orderID, OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return supplierOrderService.updateOrderStatus(orderID, orderStatus);
	}

	@Override
	public SupplierOrderDto updateOrderQuantity(Integer orderID, Integer idOrderLine, Integer quantity) {
		// TODO Auto-generated method stub
		return supplierOrderService.updateOrderQuantity(orderID, idOrderLine, quantity);
	}

	@Override
	public SupplierOrderDto updateSupplier(Integer orderID, Integer supplierID) {
		// TODO Auto-generated method stub
		return supplierOrderService.updateSupplier(orderID, supplierID);
	}

	@Override
	public SupplierOrderDto updateItem(Integer orderID, Integer orderLigneID, Integer itemID) {
		// TODO Auto-generated method stub
		return supplierOrderService.updateItem(orderID, orderLigneID, itemID);
	}

	@Override
	public SupplierOrderDto deleteItem(Integer orderID, Integer orderLigneID) {
		// TODO Auto-generated method stub
		return supplierOrderService.deleteItem(orderID, orderLigneID);
	}

	@Override
	public SupplierOrderDto findById(Integer id) {
		// TODO Auto-generated method stub
		return supplierOrderService.findById(id);
	}

	@Override
	public List<SupplierOrderDto> findAll() {
		// TODO Auto-generated method stub
		return supplierOrderService.findAll();
	}

	@Override
	public List<SupplierOrderLigneDto> findAllSupplierOrderLigneBySupplierOrderId(Integer orderID) {
		// TODO Auto-generated method stub
		return supplierOrderService.findAllSupplierOrderLigneBySupplierOrderId(orderID);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		supplierOrderService.delete(id);
	}

}

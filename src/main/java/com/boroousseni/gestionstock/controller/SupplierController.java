package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.SupplierApi;
import com.boroousseni.gestionstock.dto.SupplierDto;
import com.boroousseni.gestionstock.services.SupplierServcice;

@RestController
public class SupplierController implements SupplierApi {
	
	private SupplierServcice supplierService;

	@Autowired
	public SupplierController(SupplierServcice supplierService) {
		this.supplierService = supplierService;
	}

	@Override
	public SupplierDto save(SupplierDto dto) {
		// TODO Auto-generated method stub
		return supplierService.save(dto);
	}

	@Override
	public SupplierDto findById(Integer id) {
		// TODO Auto-generated method stub
		return supplierService.findById(id);
	}

	@Override
	public List<SupplierDto> findAll() {
		// TODO Auto-generated method stub
		return supplierService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		supplierService.delete(id);
	}

}

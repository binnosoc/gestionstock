package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.CustomerApi;
import com.boroousseni.gestionstock.dto.CustomerDto;
import com.boroousseni.gestionstock.services.CustomerService;

@RestController
public class CustomerController implements CustomerApi {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}

	@Override
	public CustomerDto save(CustomerDto dto) {
		// TODO Auto-generated method stub
		return customerService.save(dto);
	}

	@Override
	public CustomerDto findById(Integer id) {
		// TODO Auto-generated method stub
		return customerService.findById(id);
	}

	@Override
	public List<CustomerDto> findAll() {
		// TODO Auto-generated method stub
		return customerService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		customerService.delete(id);
	}

}

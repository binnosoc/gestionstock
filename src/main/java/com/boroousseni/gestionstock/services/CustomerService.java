package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerDto;

public interface CustomerService {

	  CustomerDto save(CustomerDto dto);

	  CustomerDto findById(Integer id);

	  List<CustomerDto> findAll();

	  void delete(Integer id);
}

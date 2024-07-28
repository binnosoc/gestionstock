package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.boroousseni.gestionstock.dto.CustomerDto;
import com.boroousseni.gestionstock.models.Customer;
import com.boroousseni.gestionstock.repository.CustomerRepository;
import com.boroousseni.gestionstock.services.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto save(CustomerDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}

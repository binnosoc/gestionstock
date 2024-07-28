package com.boroousseni.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.CompanyApi;
import com.boroousseni.gestionstock.dto.CompanyDto;
import com.boroousseni.gestionstock.services.CompanyService;


@RestController
public class CompanyController implements CompanyApi {

	private CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@Override
	public CompanyDto save(CompanyDto dto) {
		// TODO Auto-generated method stub
		return companyService.save(dto);
	}

	@Override
	public CompanyDto findById(Integer id) {
		// TODO Auto-generated method stub
		return companyService.findById(id);
	}

}

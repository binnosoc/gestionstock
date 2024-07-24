package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.CompanyDto;

public interface CompanyService {
	  CompanyDto save(CompanyDto dto);

	  CompanyDto findById(Integer id);

	  List<CompanyDto> findAll();

	  void delete(Integer id);
}

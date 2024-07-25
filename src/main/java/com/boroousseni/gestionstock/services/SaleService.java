package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.SaleDto;

public interface SaleService {
	
	  SaleDto save(SaleDto dto);

	  SaleDto findById(Integer id);

	  SaleDto findByCode(String code);

	  List<SaleDto> findAll();

	  void delete(Integer id);
}

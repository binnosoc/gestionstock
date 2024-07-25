package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.SupplierDto;

public interface SupplierServcice {
	  SupplierDto save(SupplierDto dto);

	  SupplierDto findById(Integer id);

	  List<SupplierDto> findAll();

	  void delete(Integer id);
}

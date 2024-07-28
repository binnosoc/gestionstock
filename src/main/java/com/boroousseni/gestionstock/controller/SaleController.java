package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.SaleApi;
import com.boroousseni.gestionstock.dto.SaleDto;
import com.boroousseni.gestionstock.services.SaleService;

@RestController
public class SaleController implements SaleApi {

	private SaleService saleService;

	@Autowired
	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}
	
	@Override
	public SaleDto save(SaleDto dto) {
		// TODO Auto-generated method stub
		return saleService.save(dto);
	}

	@Override
	public SaleDto findById(Integer id) {
		// TODO Auto-generated method stub
		return saleService.findById(id);
	}

	@Override
	public SaleDto findByCode(String code) {
		// TODO Auto-generated method stub
		return saleService.findByCode(code);
	}

	@Override
	public List<SaleDto> findAll() {
		// TODO Auto-generated method stub
		return saleService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		saleService.delete(id);
	}

}

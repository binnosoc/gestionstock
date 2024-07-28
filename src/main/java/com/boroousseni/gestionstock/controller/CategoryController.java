package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.CategoryApi;
import com.boroousseni.gestionstock.dto.CategoryDto;
import com.boroousseni.gestionstock.services.CategoryService;

@RestController
public class CategoryController implements CategoryApi {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public CategoryDto save(CategoryDto dto) {
		// TODO Auto-generated method stub
		return categoryService.save(dto);
	}

	@Override
	public CategoryDto findById(Integer id) {
		// TODO Auto-generated method stub
		return categoryService.findById(id);
	}
	
	@Override
	public List<CategoryDto>  findAllByCompanyId(Integer id) {
		// TODO Auto-generated method stub
		return categoryService.findAllByCompanyId(id);
	}

	@Override
	public CategoryDto findByCode(String code) {
		// TODO Auto-generated method stub
		return categoryService.findByCode(code);
	}

}

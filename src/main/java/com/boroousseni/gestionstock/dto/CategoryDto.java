package com.boroousseni.gestionstock.dto;

import java.util.List;

import com.boroousseni.gestionstock.models.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
	private Integer id;

	private String name;
	
	private String description;
	
	private CompanyDto company;

	@JsonIgnore
	private List<ItemDto> items;

	public static CategoryDto fromEntity(Category category) {
		if(category==null) {
			return null;
		}
		
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.company(CompanyDto.fromEntity(category.getCompany()))
				.build();
		
	}
	
	public static Category toEntity(CategoryDto categoryDto) {
		if(categoryDto==null)
			return null;
		
		Category category = new Category();
		
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setCompany(CompanyDto.toEntity(categoryDto.getCompany()));	
		
		return category;
	}
	
}

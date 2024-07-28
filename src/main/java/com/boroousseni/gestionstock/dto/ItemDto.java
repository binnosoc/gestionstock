package com.boroousseni.gestionstock.dto;

import java.util.List;

import com.boroousseni.gestionstock.models.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
	
	private Integer id;

	private String code;

	private String name;

	private String description;

	private Float unitPrice;

	private Integer stockQuantity;

	private CategoryDto category;
	
	private CompanyDto company;

	@JsonIgnore
	private List<SaleLigneDto> salesLigne;
	
	@JsonIgnore
	private List<CustomerOrderLigneDto> customerOrdersLigne;

	@JsonIgnore
	private List<SupplierOrderLigneDto> supplierOrdersLigne;
	
	public static ItemDto fromEntity(Item item) {
		if(item==null)
			return null;
			
		
		return ItemDto.builder()
				.id(item.getId())
				.code(item.getCode())
				.name(item.getName())
				.description(item.getDescription())
				.unitPrice(item.getUnitPrice())
				.stockQuantity(item.getStockQuantity())
				.category(CategoryDto.fromEntity(item.getCategory()))
				.company(CompanyDto.fromEntity(item.getCompany()))
				.build();
		
	}
	
	public static Item toEntity(ItemDto itemDto) {
		if(itemDto==null)
			return null;
		
		Item item = new Item();
		
		item.setId(itemDto.getId());
		item.setCode(itemDto.getCode());
		item.setName(itemDto.getName());
		item.setDescription(itemDto.getDescription());
		item.setUnitPrice(itemDto.getUnitPrice());
		item.setStockQuantity(itemDto.getStockQuantity());
		item.setCategory(CategoryDto.toEntity(itemDto.getCategory()));
		item.setCompany(CompanyDto.toEntity(itemDto.getCompany()));
		
		return item;
		
		
		
	}
}

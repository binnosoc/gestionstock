package com.boroousseni.gestionstock.dto;

import java.util.List;

import com.boroousseni.gestionstock.models.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
	
	private Integer itemId;

	private String itemCode;

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
				.itemId(item.getItemId())
				.itemCode(item.getItemCode())
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
		
		item.setItemId(itemDto.getItemId());
		item.setItemCode(itemDto.getItemCode());
		item.setName(itemDto.getName());
		item.setDescription(itemDto.getDescription());
		item.setUnitPrice(itemDto.getUnitPrice());
		item.setStockQuantity(itemDto.getStockQuantity());
		item.setCategory(CategoryDto.toEntity(itemDto.getCategory()));
		item.setCompany(CompanyDto.toEntity(itemDto.getCompany()));
		
		return item;
		
		
		
	}
}

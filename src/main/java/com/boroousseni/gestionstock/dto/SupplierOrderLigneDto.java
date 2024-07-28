package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.SupplierOrderLigne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierOrderLigneDto {
	
	private Integer id;
		 
	private SupplierOrderDto supplierOrder;
 
	private ItemDto item;
	
	public static SupplierOrderLigneDto fromEntity(SupplierOrderLigne supplierOrderLigne) {
		
		if(supplierOrderLigne==null) {
			return null;
		}
		
		return SupplierOrderLigneDto.builder()
				.id(supplierOrderLigne.getId())
				.supplierOrder(SupplierOrderDto.fromEntity(supplierOrderLigne.getSupplierOrder()))
				.item(ItemDto.fromEntity(supplierOrderLigne.getItem()))
				.build();
	}
	
	public static SupplierOrderLigne toEntity(SupplierOrderLigneDto supplierOrderLigneDto) {
		if(supplierOrderLigneDto==null) {
			return null;
		}
		
		SupplierOrderLigne supplierOrderLigne = new SupplierOrderLigne();
		
		supplierOrderLigne.setId(supplierOrderLigneDto.getId());
		supplierOrderLigne.setSupplierOrder(SupplierOrderDto.toEntity(supplierOrderLigneDto.getSupplierOrder()));
		supplierOrderLigne.setItem(ItemDto.toEntity(supplierOrderLigneDto.getItem()));;
		
		return supplierOrderLigne;
		
		
	}
	
}

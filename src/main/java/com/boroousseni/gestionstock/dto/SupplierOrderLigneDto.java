package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.SupplierOrderLigne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierOrderLigneDto {
	
	private Integer supplierOrderLigneID;
		 
	private SupplierOrderDto supplierOrder;
 
	private ItemDto item;
	
	public static SupplierOrderLigneDto fromEntity(SupplierOrderLigne supplierOrderLigne) {
		
		if(supplierOrderLigne==null) {
			return null;
		}
		
		return SupplierOrderLigneDto.builder()
				.supplierOrderLigneID(supplierOrderLigne.getSupplierOrderLigneID())
				.supplierOrder(SupplierOrderDto.fromEntity(supplierOrderLigne.getSupplierOrder()))
				.item(ItemDto.fromEntity(supplierOrderLigne.getItem()))
				.build();
	}
	
	public static SupplierOrderLigne toEntity(SupplierOrderLigneDto supplierOrderLigneDto) {
		if(supplierOrderLigneDto==null) {
			return null;
		}
		
		SupplierOrderLigne supplierOrderLigne = new SupplierOrderLigne();
		
		supplierOrderLigne.setSupplierOrderLigneID(supplierOrderLigneDto.getSupplierOrderLigneID());
		supplierOrderLigne.setSupplierOrder(SupplierOrderDto.toEntity(supplierOrderLigneDto.getSupplierOrder()));
		supplierOrderLigne.setItem(ItemDto.toEntity(supplierOrderLigneDto.getItem()));;
		
		return supplierOrderLigne;
		
		
	}
	
}

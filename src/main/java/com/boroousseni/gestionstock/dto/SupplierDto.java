package com.boroousseni.gestionstock.dto;

import java.util.List;

import com.boroousseni.gestionstock.models.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDto {

	private Integer supplierID;
	 
	private BaseInfoDto baseInfo;
 
	@JsonIgnore
	private List<SupplierOrderDto> supplierOrders;
 
	private CompanyDto company;

	public static SupplierDto fromEntity(Supplier supplier) {
		// TODO Auto-generated method stub
		
		if(supplier==null) {
			return null;
		}
		return SupplierDto.builder()
				.supplierID(supplier.getSupplierID())
				.baseInfo(BaseInfoDto.fromEntity(supplier.getBaseInfo()))
				.company(CompanyDto.fromEntity(supplier.getCompany()))
				.build()
				
				;
	}

	public static Supplier toEntity(SupplierDto supplierDto) {
		
		if(supplierDto==null) {
			return null;
		}
		Supplier supplier = new Supplier();
		
		supplier.setSupplierID(supplierDto.getSupplierID());
		supplier.setBaseInfo(BaseInfoDto.toEntity(supplierDto.getBaseInfo()));
		supplier.setCompany(CompanyDto.toEntity(supplierDto.getCompany()));
		
		
		return supplier;
	}
	
	
}

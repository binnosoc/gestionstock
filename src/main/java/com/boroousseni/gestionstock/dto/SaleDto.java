package com.boroousseni.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.boroousseni.gestionstock.models.Sale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDto {
	 
		private Integer id;
	 
		private String code;
	 
		private String description;
	 
		private Instant saleDate;
	 
		private List<SaleLigneDto> saleLignes;
	 
		private CompanyDto company;

		public static SaleDto fromEntity(Sale sale) {
			// TODO Auto-generated method stub
			
			if(sale==null)
			{
				return null;
			}
			
			
			return SaleDto.builder()
					.id(sale.getId())
					.code(sale.getCode())
					.description(sale.getDescription())
					.saleDate(sale.getSaleDate())
					.company(CompanyDto.fromEntity(sale.getCompany()))
					.build()
					
					;
			
		}

		public static Sale toEntity(SaleDto saleDto) {
			// TODO Auto-generated method stub
			if(saleDto==null)
			{
				return null;
			}
			Sale sale= new Sale();
			
			sale.setId(saleDto.getId());
			sale.setCode(saleDto.getCode());
			sale.setDescription(saleDto.getDescription());
			sale.setSaleDate(saleDto.getSaleDate());
			sale.setCompany(CompanyDto.toEntity(saleDto.getCompany()));
			
			
			return sale;
		}

}

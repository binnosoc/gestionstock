package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.SaleLigne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleLigneDto {
	 
		private Integer saleLigneID;
	 
		private SaleDto sale;
	 
		private ItemDto item;
		
		public static SaleLigneDto fromEntity(SaleLigne saleLigne) {
			if(saleLigne==null) {
				return null;
			}
			
			return SaleLigneDto.builder()
					.saleLigneID(saleLigne.getSaleLigneID())
					.sale(SaleDto.fromEntity(saleLigne.getSale()))
					.item(ItemDto.fromEntity(saleLigne.getItem()))
					.build()
					;
		}
		
		public static SaleLigne toEntity(SaleLigneDto saleLigneDto) {
			if(saleLigneDto==null) {
				return null;
			}
			
			SaleLigne saleLigne = new SaleLigne();
			saleLigne.setSaleLigneID(saleLigneDto.getSaleLigneID());
			saleLigne.setSale(SaleDto.toEntity(saleLigneDto.getSale()));
			saleLigne.setItem(ItemDto.toEntity(saleLigneDto.getItem()));
			return saleLigne;
		}
}

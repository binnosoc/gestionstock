package com.boroousseni.gestionstock.dto;


import com.boroousseni.gestionstock.models.CustomerOrderLigne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerOrderLigneDto {
	private Integer customerOrderLigneID;

	private CustomerOrderDto customerOrder;

	private ItemDto item;
	
	public static CustomerOrderLigneDto fromEntity(CustomerOrderLigne customerOrderLigne) {
		if(customerOrderLigne==null)
			return null;
		
		return CustomerOrderLigneDto.builder()
				.customerOrderLigneID(customerOrderLigne.getCustomerOrderLigneID())
				.customerOrder(CustomerOrderDto.fromEntity(customerOrderLigne.getCustomerOrder()))
				.item(ItemDto.fromEntity(customerOrderLigne.getItem()))
				.build();
	}
	
	public static CustomerOrderLigne toEntity(CustomerOrderLigneDto customerOrderLigneDto) {
		if(customerOrderLigneDto==null)
			return null;
		
		CustomerOrderLigne customerOrderLigne = new CustomerOrderLigne();
		
		customerOrderLigne.setCustomerOrderLigneID(customerOrderLigneDto.getCustomerOrderLigneID());
		customerOrderLigne.setCustomerOrder(CustomerOrderDto.toEntity(customerOrderLigneDto.getCustomerOrder()));
		customerOrderLigne.setItem(ItemDto.toEntity(customerOrderLigneDto.getItem()));
		
		
		
		return customerOrderLigne;
		
	}
}

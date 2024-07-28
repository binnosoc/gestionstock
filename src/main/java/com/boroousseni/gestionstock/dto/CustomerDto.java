package com.boroousseni.gestionstock.dto;

import java.util.List;

import com.boroousseni.gestionstock.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

	private Integer id;

	private BaseInfoDto baseInfo;

	@JsonIgnore
	private List<CustomerOrderDto> customerOrders;

	private CompanyDto company;

	public static CustomerDto fromEntity(Customer customer) {
		if (customer == null) {
			return null;
		}
		return CustomerDto.builder().id(customer.getId())
				.baseInfo(BaseInfoDto.fromEntity(customer.getBaseInfo()))
				.company(CompanyDto.fromEntity(customer.getCompany())).build();
	}

	public static Customer toEntity(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		if (customerDto == null) {
			return null;
		}

		Customer customer = new Customer();

		customer.setId(customerDto.getId());
		customer.setBaseInfo(BaseInfoDto.toEntity(customerDto.getBaseInfo()));
		customer.setCompany(CompanyDto.toEntity(customerDto.getCompany()));

		return customer;
	}

}

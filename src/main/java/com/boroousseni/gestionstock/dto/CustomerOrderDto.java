package com.boroousseni.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.boroousseni.gestionstock.models.CustomerOrder;
import com.boroousseni.gestionstock.models.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerOrderDto {

	private Integer customerOrderID;
	
	private Instant orderDate;

	private Float totalAmount;
	
	private OrderStatus status;
	
	private Integer quantity;	

	private CustomerDto customer;

	private CompanyDto company;
	
	@JsonIgnore
	private List<CustomerOrderLigneDto> customerOrdersLigne;

	public static CustomerOrderDto fromEntity(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		if(customerOrder == null)
			return null;
		return CustomerOrderDto.builder()
				.customerOrderID(customerOrder.getCustomerOrderID())
				.orderDate(customerOrder.getOrderDate())
				.totalAmount(customerOrder.getTotalAmount())
				.quantity(customerOrder.getQuantity())
				.status(customerOrder.getStatus())
				.customer(CustomerDto.fromEntity(customerOrder.getCustomer()))
				.company(CompanyDto.fromEntity(customerOrder.getCompany()))
				.build();
		
	}

	public static CustomerOrder toEntity(CustomerOrderDto customerOrderDto) {
		// TODO Auto-generated method stub
		if(customerOrderDto == null)
			return null;
		
		CustomerOrder customerOrder = new CustomerOrder();
		
		customerOrder.setCustomerOrderID(customerOrderDto.getCustomerOrderID());
		customerOrder.setOrderDate(customerOrderDto.getOrderDate());
		customerOrder.setTotalAmount(customerOrderDto.getTotalAmount());
		customerOrder.setQuantity(customerOrderDto.getQuantity());
		customerOrder.setStatus(customerOrderDto.getStatus());
		customerOrder.setCustomer(CustomerDto.toEntity(customerOrderDto.getCustomer()));
		customerOrder.setCompany(CompanyDto.toEntity(customerOrderDto.getCompany()));
		
		return customerOrder;
		
	}
	
	
}

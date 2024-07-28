package com.boroousseni.gestionstock.dto;

import java.util.List;


import com.boroousseni.gestionstock.models.Category;
import com.boroousseni.gestionstock.models.Company;
import com.boroousseni.gestionstock.models.Customer;
import com.boroousseni.gestionstock.models.Item;
import com.boroousseni.gestionstock.models.Sale;
import com.boroousseni.gestionstock.models.Supplier;
import com.boroousseni.gestionstock.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {

	private Integer id;	
	
	private String name;
		
	private String phone;
	
	private String code;
	
	private String email;
	
	private String picture;
	
	private AddressDto address;
		
	@JsonIgnore
	private List<Item> items;
			
	@JsonIgnore
	private List<Category> categories;
		
	@JsonIgnore
	private List<User> users;
		
	@JsonIgnore
	private List<Sale> sales;
		
	@JsonIgnore
	private List<Customer> customers;
		
	@JsonIgnore
	private List<Supplier> suppliers;
	
	public static CompanyDto fromEntity(Company company) {
		if(company==null) {
			return null;
		}
		
		return CompanyDto.builder()
				.id(company.getId())
				.name(company.getName())
				.phone(company.getPhone())
				.code(company.getCode())
				.email(company.getEmail())
				.picture(company.getPicture())
				.address(AddressDto.fromEntity(company.getAddress()))
				.build();
	}
	
	public static Company toEntity(CompanyDto companyDto) {
		if(companyDto==null) {
			return null;
					
		}
		
		Company company=new Company();
		
		company.setId(companyDto.getId());
		company.setName(companyDto.getName());
		company.setPhone(companyDto.getPhone());
		company.setCode(companyDto.getCode());
		company.setEmail(companyDto.getEmail());
		company.setPicture(companyDto.getPicture());
		company.setAddress(AddressDto.toEntity(companyDto.getAddress()));
		
		return company;
	}
	
}

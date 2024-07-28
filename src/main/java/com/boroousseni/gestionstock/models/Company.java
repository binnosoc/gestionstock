package com.boroousseni.gestionstock.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name="companies")

public class Company extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "picture")
	private String picture;
	
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "company")
	private List<Item> items;
	
	@OneToMany(mappedBy = "company")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "company")
	private List<User> users;
	
	@OneToMany(mappedBy = "company")
	private List<Sale> sales;
	
	@OneToMany(mappedBy = "company")
	private List<Customer> customers;
	
	@OneToMany(mappedBy = "company")
	private List<Supplier> suppliers;
}

package com.boroousseni.gestionstock.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "items")
public class Item extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "unitPrice")
	private Float unitPrice;

	@Column(name = "stockQuantity")
	private Integer stockQuantity;

	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;
	

	@OneToMany(mappedBy = "item")
	private List<StockMovement> stockMovements;
	
	@OneToMany(mappedBy = "item")
	private List<SaleLigne> salesLigne;
	
	@OneToMany(mappedBy = "item")
	private List<CustomerOrderLigne> customerOrdersLigne;
	
	@OneToMany(mappedBy = "item")
	private List<SupplierOrderLigne> supplierOrdersLigne;



}

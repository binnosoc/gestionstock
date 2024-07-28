package com.boroousseni.gestionstock.models;


import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="suppliers")
public class Supplier extends AbstractEntity {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Embedded
	private BaseInfo baseInfo;
	
	@OneToMany(mappedBy = "supplier")
	private List<SupplierOrder> supplierOrders;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;
	
	
}

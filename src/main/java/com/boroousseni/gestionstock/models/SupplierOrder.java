package com.boroousseni.gestionstock.models;


import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="supplierOrders")
public class SupplierOrder extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer supplierOrderID;
	
	
	@Column(name="orderDate")
	private Instant orderDate;
	
	@Column(name="totalAmount")
	private Float totalAmount;
	
	
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "supplierID")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "supplierOrder")
	private List<SupplierOrderLigne> supplierOrdersLigne;	
	
}


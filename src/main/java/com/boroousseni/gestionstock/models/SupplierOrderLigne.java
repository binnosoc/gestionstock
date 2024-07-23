package com.boroousseni.gestionstock.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="supplierOrdersLigne")
public class SupplierOrderLigne extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer supplierOrderLigneID;
	
	@ManyToOne
	@JoinColumn(name = "supplierOrderID")
	private SupplierOrder supplierOrder;
	
	@ManyToOne
	@JoinColumn(name = "itemID")
	private Item item;

}


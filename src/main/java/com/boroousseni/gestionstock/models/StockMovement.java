package com.boroousseni.gestionstock.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "stocksMovement")

public class StockMovement extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "stockMovementDate")
	private Instant stockMovementDate;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "typemvt")
	@Enumerated(EnumType.STRING)
	private TypeOfStock typeOfStock;

	@Column(name = "sourcemvt")
	@Enumerated(EnumType.STRING)
	private StockSource stockSource;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "itemID")
	private Item item;
}

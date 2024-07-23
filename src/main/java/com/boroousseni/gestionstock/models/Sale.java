package com.boroousseni.gestionstock.models;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
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
@Table(name="sales")
public class Sale extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer saleID;

	@Column(name="code")
	private String code;
	
	@Column(name="description")
	private String description;
	
	@Column(name="saleDate")
	private Instant saleDate;
	
	@OneToMany(mappedBy = "sale")
	private List<SaleLigne> saleLignes;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;

}

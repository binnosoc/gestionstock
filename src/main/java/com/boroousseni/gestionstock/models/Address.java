package com.boroousseni.gestionstock.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Builder

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;
	

	@Column(name = "country")
	private String country;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "postalCode")
	private String postalCode;
}

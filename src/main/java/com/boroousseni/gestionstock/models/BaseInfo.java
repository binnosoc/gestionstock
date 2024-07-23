package com.boroousseni.gestionstock.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
@Builder

public class BaseInfo implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "picture")
	private String picture;
	
	
	@Embedded
	private Address address;	

}

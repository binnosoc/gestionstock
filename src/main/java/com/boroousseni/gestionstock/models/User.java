package com.boroousseni.gestionstock.models;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name="users")

public class User extends AbstractEntity {
	

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private Integer userID;
	
	
	@Embedded
	private BaseInfo baseInfo;
	
	@Column(name="password")
	private String password;

	@Column(name="roles")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;
	
	
}

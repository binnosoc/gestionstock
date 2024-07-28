package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	private Integer id;
	 
	private String name;
 
	@JsonIgnore
	private UserDto user;

	public static Role toEntity(RoleDto roleDto) {
		// TODO Auto-generated method stub
		if(roleDto==null) {
			return null;
		}
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());		
		return role;
	}

	public static RoleDto fromEntity(Role role) {
		// TODO Auto-generated method stub
		if(role==null) {
			return null;
		}
		
		return RoleDto.builder()
				.id(role.getId())
				.name(role.getName())
				.user(UserDto.fromEntity(role.getUser()))
				.build()
				;
	}
	
	
}

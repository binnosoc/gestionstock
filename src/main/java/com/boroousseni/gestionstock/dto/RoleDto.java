package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	private Integer roleID;
	 
	private String roleName;
 
	private UserDto user;

	public static Role toEntity(RoleDto roleDto) {
		// TODO Auto-generated method stub
		if(roleDto==null) {
			return null;
		}
		Role role = new Role();
		role.setRoleID(roleDto.getRoleID());
		role.setRoleName(roleDto.getRoleName());
		role.setUser(UserDto.toEntity(roleDto.getUser()));
		return role;
	}

	public static RoleDto fromEntity(Role role) {
		// TODO Auto-generated method stub
		if(role==null) {
			return null;
		}
		
		return RoleDto.builder()
				.roleID(role.getRoleID())
				.roleName(role.getRoleName())
				.user(UserDto.fromEntity(role.getUser()))
				.build()
				;
	}
	
	
}

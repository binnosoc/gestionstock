package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	 
		private Integer userID;
	 
		private BaseInfoDto baseInfo;
	 
		private String password;
	 
		private RoleDto role;
	 
		private CompanyDto company;
		
		public static UserDto fromEntity(User user) {
			if(user==null) {
				return null;
			}
			
			return UserDto.builder()
					.userID(user.getUserID())
					.baseInfo(BaseInfoDto.fromEntity(user.getBaseInfo()))
					.password(user.getPassword())
					.role(RoleDto.fromEntity(user.getRole()))
					.company(CompanyDto.fromEntity(user.getCompany()))
					.build()
					;
		}
		
		public static User toEntity(UserDto userDto) {
			if(userDto==null) {
				return null;
			}
			
			User user = new User();
			user.setUserID(userDto.getUserID());
			user.setBaseInfo(BaseInfoDto.toEntity(userDto.getBaseInfo()));
			user.setPassword(userDto.getPassword());
			user.setRole(RoleDto.toEntity(userDto.getRole()));
			user.setCompany(CompanyDto.toEntity(userDto.getCompany()));
			
			return user;
		}
		
}

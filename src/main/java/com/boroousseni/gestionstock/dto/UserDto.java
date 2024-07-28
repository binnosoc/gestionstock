package com.boroousseni.gestionstock.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.boroousseni.gestionstock.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	 
		private Integer id;
	 
		private BaseInfoDto baseInfo;
	 
		private String password;
	 
		private List<RoleDto> role;
	 
		private CompanyDto company;
		
		public static UserDto fromEntity(User user) {
			if(user==null) {
				return null;
			}
			
			return UserDto.builder()
					.id(user.getId())
					.baseInfo(BaseInfoDto.fromEntity(user.getBaseInfo()))
					.password(user.getPassword())
					.role(
				            user.getRole() != null ?
				                user.getRole().stream()
				                    .map(RoleDto::fromEntity)
				                    .collect(Collectors.toList()) : null
				        )
					.company(CompanyDto.fromEntity(user.getCompany()))
					.build()
					;
		}
		
		public static User toEntity(UserDto userDto) {
			if(userDto==null) {
				return null;
			}
			
			User user = new User();
			user.setId(userDto.getId());
			user.setBaseInfo(BaseInfoDto.toEntity(userDto.getBaseInfo()));
			user.setPassword(userDto.getPassword());			
			user.setCompany(CompanyDto.toEntity(userDto.getCompany()));
			
			return user;
		}
		
}

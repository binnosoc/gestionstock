package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private String firstname;
    private String lastname;
  
    private String email;
   
    private boolean accountLocked;
    private boolean enabled;
    
    public static UserDto fromEntity(User user) {
    	if(user == null) {
			return null;
		}
    	
    	return UserDto.builder()
    			.firstname(user.getFirstname())
    			.lastname(user.getLastname())
    			.email(user.getEmail())
    			.accountLocked(user.isAccountLocked())
    			.enabled(user.isEnabled())
    			.build();
    	
    }
    
    public static User fromEntity(UserDto userDto) {
    	if(userDto==null) {
    		return null;
    	}
    	
    	User user = new User();
    	
    	user.setFirstname(userDto.getFirstname());
    	user.setLastname(userDto.getLastname());
    	user.setEmail(userDto.getEmail());
    	user.setAccountLocked(userDto.isAccountLocked());
    	user.setEnabled(userDto.isEnabled());
    	
    	
    	
    	return user;
    }
}

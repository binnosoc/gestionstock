package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.UserDto;

public class UserValidator {
	public static List<String> validate(UserDto userDto){
		 
		 List<String> errors = new ArrayList<>();
		 
		 if(userDto==null) {
			 errors.add("Veuillez renseigner le mot de passe");
			 return errors;
		 }
		 
		 errors.addAll(BaseInfoValidator.validate(userDto.getBaseInfo()));
		 return errors;

			
	}
}

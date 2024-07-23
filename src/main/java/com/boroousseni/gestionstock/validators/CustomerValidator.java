package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerDto;

public class CustomerValidator {
	public static List<String> validate(CustomerDto  customerDto){
		 
		 List<String> errors = new ArrayList<>();
		 
		 if(customerDto==null) {			
			 return errors;
		 }
		 
		 errors.addAll(BaseInfoValidator.validate(customerDto.getBaseInfo()));
		 
		 return errors;

			
	}
}

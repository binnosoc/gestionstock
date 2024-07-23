package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.SupplierDto;

public class SupplierValidator {
	public static List<String> validate(SupplierDto supplierDto){
		 
		 List<String> errors = new ArrayList<>();
		 
		 if(supplierDto==null) {
			 return errors;
		 }
		 
		 errors.addAll(BaseInfoValidator.validate(supplierDto.getBaseInfo()));
		 return errors;

			
	}
}

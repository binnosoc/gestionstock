package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.SaleDto;

import io.micrometer.common.util.StringUtils;

public class SaleValidator {
	public static List<String> validate(SaleDto saleDto){
		 
		 List<String> errors = new ArrayList<>();
		 
		 if(saleDto==null) {
			 errors.add("Veuillez renseigner la description'");
			 errors.add("Veuillez renseigner la date de vente'");
			 return errors;
		 }
		 
		 if(StringUtils.isEmpty(saleDto.getDescription())) {
			 errors.add("Veuillez renseigner la description'");
		 }
		 
		 if(saleDto.getSaleDate()==null) {
			 errors.add("Veuillez renseigner la date de vente'");
		 }
		 
		 return errors;
			
	}
}

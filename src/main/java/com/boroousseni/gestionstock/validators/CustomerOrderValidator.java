package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.CustomerOrderDto;

public class CustomerOrderValidator {
	public static List<String> validate(CustomerOrderDto customerOrderDto) {

		List<String> errors = new ArrayList<>();

		if (customerOrderDto == null) {
			errors.add("Veuillez renseigner la quantité");
			errors.add("Veuillez renseigner la date");
			return errors;
		}

		if (customerOrderDto.getQuantity() == null) {
			errors.add("Veuillez renseigner la quantité");
		}

		if (customerOrderDto.getOrderDate() == null) {
			errors.add("Veuillez renseigner la date");
		}
		
		errors.addAll(CustomerValidator.validate(customerOrderDto.getCustomer()));

		return errors;

	}
}

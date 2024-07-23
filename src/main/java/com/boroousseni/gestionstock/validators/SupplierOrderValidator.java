package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.SupplierOrderDto;

public class SupplierOrderValidator {
	public static List<String> validate(SupplierOrderDto supplierOrderDto) {

		List<String> errors = new ArrayList<>();

		if (supplierOrderDto == null) {

			errors.add("Veuillez renseigner la quantité");
			errors.add("Veuillez renseigner la date");

			return errors;
		}
		if (supplierOrderDto.getOrderDate() == null) {
			errors.add("Veuillez renseigner la date");
		}
		if (supplierOrderDto.getQuantity() == null) {
			errors.add("Veuillez renseigner la quantité");
		}

		errors.addAll(SupplierValidator.validate(supplierOrderDto.getSupplier()));
		return errors;

	}
}

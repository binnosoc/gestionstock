package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.AddressDto;

import io.micrometer.common.util.StringUtils;

public class AddressValidator {
	public static List<String> validate(AddressDto addressDto) {

		List<String> errors = new ArrayList<>();

		if (addressDto == null) {
			errors.add("Veuillez renseigner l'adresse 1'");
			errors.add("Veuillez renseigner la ville'");
			errors.add("Veuillez renseigner le pays'");
			errors.add("Veuillez renseigner le code postal'");
			return errors;
		}

		if (StringUtils.isEmpty(addressDto.getAddress1())) {
			errors.add("Veuillez renseigner l'adresse 1'");
		}
		if (StringUtils.isEmpty(addressDto.getAddress2())) {
			errors.add("Veuillez renseigner l'adresse 2'");
		}
		if (StringUtils.isEmpty(addressDto.getCountry())) {
			errors.add("Veuillez renseigner le pays'");
		}
		if (StringUtils.isEmpty(addressDto.getTown())) {
			errors.add("Veuillez renseigner la ville '");
		}
		if (StringUtils.isEmpty(addressDto.getPostalCode())) {
			errors.add("Veuillez renseigner le code postale '");
		}

		return errors;

	}
}

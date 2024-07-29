package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.boroousseni.gestionstock.dto.CompanyDto;


public class CompanyValidator {
	public static List<String> validate(CompanyDto companyDto) {

		List<String> errors = new ArrayList<>();

		if (companyDto == null) {
			errors.add("Veuillez renseigner le nom de l'entreprise");

			return errors;
		}
		if (!StringUtils.hasLength(companyDto.getName())) {
			errors.add("Veuillez renseigner le nom de l'entreprise");
		}

		if (!StringUtils.hasLength(companyDto.getEmail())) {
			errors.add("Veuillez renseigner le mail de l'entreprise");
		}

		if (!StringUtils.hasLength(companyDto.getPhone())) {
			errors.add("Veuillez renseigner le phone de l'entreprise");
		}

		if (!StringUtils.hasLength(companyDto.getPicture())) {
			errors.add("Veuillez renseigner la photo de l'entreprise");
		}

		errors.addAll(AddressValidator.validate(companyDto.getAddress()));

		return errors;

	}
}

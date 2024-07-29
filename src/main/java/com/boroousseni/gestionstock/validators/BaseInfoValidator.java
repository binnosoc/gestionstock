package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.boroousseni.gestionstock.dto.BaseInfoDto;



public class BaseInfoValidator {
	public static List<String> validate(BaseInfoDto baseInfoDto) {

		List<String> errors = new ArrayList<>();

		if (baseInfoDto == null) {

			errors.add("Veuillez renseigner le nom");
			errors.add("Veuillez renseigner le prénom");
			errors.add("Veuillez renseigner le numéro de téléphone");
			errors.add("Veuillez renseigner l'adresse email");
			errors.add("Veuillez renseigner l'image");
			return errors;
		}

		if (!StringUtils.hasLength(baseInfoDto.getName())) {
			errors.add("Veuillez renseigner le nom");
		}

		if (!StringUtils.hasLength(baseInfoDto.getFirstname())) {
			errors.add("Veuillez renseigner le prénom");
		}
		if (!StringUtils.hasLength(baseInfoDto.getPhone())) {
			errors.add("Veuillez renseigner le numéro de téléphone");
		}

		if (!StringUtils.hasLength(baseInfoDto.getEmail())) {
			errors.add("Veuillez renseigner l'adresse email");
		}

		if (!StringUtils.hasLength(baseInfoDto.getPicture())) {
			errors.add("Veuillez renseigner l'image");
		}
		errors.addAll(AddressValidator.validate(baseInfoDto.getAddress()));
		return errors;

	}
}

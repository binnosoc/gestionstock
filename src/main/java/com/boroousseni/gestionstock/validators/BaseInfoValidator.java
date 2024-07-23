package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.BaseInfoDto;

import io.micrometer.common.util.StringUtils;

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

		if (StringUtils.isEmpty(baseInfoDto.getName())) {
			errors.add("Veuillez renseigner le nom");
		}

		if (StringUtils.isEmpty(baseInfoDto.getFirstname())) {
			errors.add("Veuillez renseigner le prénom");
		}
		if (StringUtils.isEmpty(baseInfoDto.getPhone())) {
			errors.add("Veuillez renseigner le numéro de téléphone");
		}

		if (StringUtils.isEmpty(baseInfoDto.getEmail())) {
			errors.add("Veuillez renseigner l'adresse email");
		}

		if (StringUtils.isEmpty(baseInfoDto.getPicture())) {
			errors.add("Veuillez renseigner l'image");
		}
		errors.addAll(AddressValidator.validate(baseInfoDto.getAddress()));
		return errors;

	}
}

package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.CategoryDto;

import io.micrometer.common.util.StringUtils;

public class CategoryValidator {
	public static List<String> validate(CategoryDto categoryDto) {

		List<String> errors = new ArrayList<>();

		if (categoryDto == null) {
			errors.add("Veuillez renseigner le nom de la catégorie");
			errors.add("Veuillez renseigner la description");

			return errors;
		}
		if (StringUtils.isEmpty(categoryDto.getName())) {
			errors.add("Veuillez renseigner le nom de la catégorie");
		}

		if (StringUtils.isEmpty(categoryDto.getDescription())) {
			errors.add("Veuillez renseigner la description");
		}

		return errors;

	}
}

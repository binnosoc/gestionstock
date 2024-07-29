package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.boroousseni.gestionstock.dto.CategoryDto;



public class CategoryValidator {
	public static List<String> validate(CategoryDto categoryDto) {

		List<String> errors = new ArrayList<>();

		if (categoryDto == null) {
			errors.add("Veuillez renseigner le nom de la catégorie");
			errors.add("Veuillez renseigner la description");

			return errors;
		}
		if (!StringUtils.hasLength(categoryDto.getName())) {
			errors.add("Veuillez renseigner le nom de la catégorie");
		}

		if (!StringUtils.hasLength(categoryDto.getDescription())) {
			errors.add("Veuillez renseigner la description");
		}

		return errors;

	}
}

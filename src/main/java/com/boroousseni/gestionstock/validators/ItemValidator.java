package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.ItemDto;

import io.micrometer.common.util.StringUtils;

public class ItemValidator {
	public static List<String> validate(ItemDto itemDto) {

		List<String> errors = new ArrayList<>();

		if (itemDto == null) {
			errors.add("Veuillez renseigner le code de l'article'");
			errors.add("Veuillez renseigner la designation'");
			errors.add("Veuillez renseigner la description'");
			errors.add("Veuillez renseigner le prix unitaire'");
			errors.add("Veuillez renseigner la quantité du stock'");
			return errors;
		}

		if (StringUtils.isEmpty(itemDto.getCode())) {
			errors.add("Veuillez renseigner le code de l'article'");
		}

		if (StringUtils.isEmpty(itemDto.getName())) {
			errors.add("Veuillez renseigner la designation'");
		}

		if (StringUtils.isEmpty(itemDto.getDescription())) {
			errors.add("Veuillez renseigner la description'");
		}

		

		if (itemDto.getUnitPrice()==null) {
			errors.add("Veuillez renseigner le prix unitaire'");
		}

		if (itemDto.getStockQuantity()==null) {
			errors.add("Veuillez renseigner la quantité du stock'");
		}

		return errors;

	}
}

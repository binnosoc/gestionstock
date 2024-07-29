package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.boroousseni.gestionstock.dto.ItemDto;

 

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

		if (!StringUtils.hasLength(itemDto.getCode())) {
			errors.add("Veuillez renseigner le code de l'article'");
		}

		if (!StringUtils.hasLength(itemDto.getName())) {
			errors.add("Veuillez renseigner la designation'");
		}

		if (!StringUtils.hasLength(itemDto.getDescription())) {
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

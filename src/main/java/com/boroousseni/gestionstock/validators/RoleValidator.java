package com.boroousseni.gestionstock.validators;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.dto.RoleDto;

import io.micrometer.common.util.StringUtils;

public class RoleValidator {
	public static List<String> validate(RoleDto roleDto) {

		List<String> errors = new ArrayList<>();

		if (roleDto == null) {
			errors.add("Veuillez renseigner le role'");
			return errors;
		}

		if (StringUtils.isEmpty(roleDto.getName())) {
			errors.add("Veuillez renseigner le role'");
		}

		return errors;

	}
}

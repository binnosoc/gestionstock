package com.boroousseni.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangePasswordDto {
	  private Integer id;

	  private String password;

	  private String confirmedPassword;
}

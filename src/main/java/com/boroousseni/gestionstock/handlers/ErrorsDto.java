package com.boroousseni.gestionstock.handlers;

import java.util.ArrayList;
import java.util.List;

import com.boroousseni.gestionstock.exceptions.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorsDto  {
	  private Integer httpCode;

	  private ErrorCode code;

	  private String message;

	  private List<String> errors = new ArrayList<>();
}

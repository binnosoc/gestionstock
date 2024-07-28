package com.boroousseni.gestionstock.handlers;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;



@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorsDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.NOT_FOUND;
    final ErrorsDto errorDto = ErrorsDto.builder()
        .code(exception.getErrorCode())
        .httpCode(notFound.value())
        .message(exception.getMessage())
        .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<ErrorsDto> handleException(InvalidOperationException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.BAD_REQUEST;
    final ErrorsDto errorDto = ErrorsDto.builder()
        .code(exception.getErrorCode())
        .httpCode(notFound.value())
        .message(exception.getMessage())
        .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidEntityException.class)
  public ResponseEntity<ErrorsDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    final ErrorsDto errorDto = ErrorsDto.builder()
        .code(exception.getErrorCode())
        .httpCode(badRequest.value())
        .message(exception.getMessage())
        .errors(exception.getErrors())
        .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }

//  @ExceptionHandler(BadCredentialsException.class)
//  public ResponseEntity<ErrorsDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
//    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//
//    final ErrorsDto errorDto = ErrorsDto.builder()
//        .code(ErrorCode.BAD_CREDENTIALS)
//        .httpCode(badRequest.value())
//        .message(exception.getMessage())
//        .errors(Collections.singletonList("Login et / ou mot de passe incorrect"))
//        .build();
//
//    return new ResponseEntity<>(errorDto, badRequest);
//  }
//
}


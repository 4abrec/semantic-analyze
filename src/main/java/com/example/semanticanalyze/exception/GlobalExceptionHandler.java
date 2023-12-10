package com.example.semanticanalyze.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.example.semanticanalyze.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ExceptionDto> handleStudentNotFoundException(Exception exception) {
    exception.printStackTrace();
    return ResponseEntity.internalServerError().body(new ExceptionDto(INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
  }

}

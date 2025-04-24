package com.alquimiasoft.minegocio.handler;

import com.alquimiasoft.minegocio.handler.error.MiNegocioError;
import com.alquimiasoft.minegocio.handler.exception.DuplicateEntityException;
import com.alquimiasoft.minegocio.handler.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MiNegocioExceptionHandler {

  @ExceptionHandler(DuplicateEntityException.class)
  public ResponseEntity<MiNegocioError> handleDuplicateEntityException(DuplicateEntityException e) {
    return new ResponseEntity<>(
        new MiNegocioError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<MiNegocioError> handleEntityNotFoundException(EntityNotFoundException e) {
    return new ResponseEntity<>(
        new MiNegocioError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<MiNegocioError> handleUnexpectedException(Exception ex) {
    return new ResponseEntity<>(
        new MiNegocioError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred. Please try again later."),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

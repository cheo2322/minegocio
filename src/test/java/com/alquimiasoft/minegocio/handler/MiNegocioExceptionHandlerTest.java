package com.alquimiasoft.minegocio.handler;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alquimiasoft.minegocio.handler.error.MiNegocioError;
import com.alquimiasoft.minegocio.handler.exception.DuplicateEntityException;
import com.alquimiasoft.minegocio.handler.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MiNegocioExceptionHandlerTest {

  private final MiNegocioExceptionHandler exceptionHandler = new MiNegocioExceptionHandler();

  @Test
  void testHandleDuplicateEntityException() {
    DuplicateEntityException exception = new DuplicateEntityException("Duplicated entity found.");

    ResponseEntity<MiNegocioError> response =
        exceptionHandler.handleDuplicateEntityException(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().httpStatus());
    assertEquals("Duplicated entity found.", response.getBody().message());
  }

  @Test
  void testHandleEntityNotFoundException() {
    EntityNotFoundException exception = new EntityNotFoundException("Entity not found.");

    ResponseEntity<MiNegocioError> response =
        exceptionHandler.handleEntityNotFoundException(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().httpStatus());
    assertEquals("Entity not found.", response.getBody().message());
  }

  @Test
  void testHandleUnexpectedException() {
    Exception exception = new Exception();

    ResponseEntity<MiNegocioError> response = exceptionHandler.handleUnexpectedException(exception);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().httpStatus());
    assertEquals(
        "An unexpected error occurred. Please try again later.", response.getBody().message());
  }
}

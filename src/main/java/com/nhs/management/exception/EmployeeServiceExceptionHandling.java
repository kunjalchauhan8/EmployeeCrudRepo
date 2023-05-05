package com.nhs.management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class EmployeeServiceExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> genericExceptionHandling(
            Exception ex,
            HttpServletRequest request) {

        log.error("Exception delivered: " +
                ex.getLocalizedMessage() +
                " for " +
                request.getRequestURI());

        return new ResponseEntity<>(
                Error.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .request(request.getRequestURI())
                        .requestType(request.getMethod())
                        .customMessage("Could not process request")
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

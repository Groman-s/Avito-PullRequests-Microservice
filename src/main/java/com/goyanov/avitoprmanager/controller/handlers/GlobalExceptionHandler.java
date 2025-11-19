package com.goyanov.avitoprmanager.controller.handlers;

import com.goyanov.avitoprmanager.controller.exceptions.ResourceNotFoundException;
import com.goyanov.avitoprmanager.controller.responses.UnsuccessfulResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.valueOf(404)).body(
                UnsuccessfulResponse.withError("NOT_FOUND", "resource not found")
        );
    }
}

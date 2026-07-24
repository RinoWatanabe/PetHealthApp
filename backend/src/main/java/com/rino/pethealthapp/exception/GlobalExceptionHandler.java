package com.rino.pethealthapp.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rino.pethealthapp.dto.response.ErrorResponse;
import com.rino.pethealthapp.dto.response.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        List<ValidationErrorResponse> errors = new ArrayList<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {

            ValidationErrorResponse validationError = new ValidationErrorResponse(
                    fieldError.getField(),
                    fieldError.getDefaultMessage());

            errors.add(validationError);
        }

        ErrorResponse errorResponse = new ErrorResponse(errors);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);

    }

}

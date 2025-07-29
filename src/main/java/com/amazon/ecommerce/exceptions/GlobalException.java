package com.amazon.ecommerce.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazon.ecommerce.responses.ApiResponse;



@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleResourceNotFound(ResourceNotFoundException e){
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleInternalServerError(Exception e){
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler(ResourceAlreadyExistedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleResourceAlreadyExisted(ResourceAlreadyExistedException e){
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

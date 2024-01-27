package com.example.Productmicroservice.exceptions;

import com.example.Productmicroservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse> handlingProductNotFoundException(ProductNotFoundException exception){
        String msg= exception.getMessage();

        ApiResponse response= ApiResponse.builder()
                .message(msg)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

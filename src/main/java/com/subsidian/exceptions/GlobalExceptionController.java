package com.subsidian.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler{
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppException> handler_for_failed_exceptions(){
        //returns global exception for unhandled exceptions
        AppException appException = new AppException("Something went wrong");
        return  new ResponseEntity<>(appException, HttpStatus.FORBIDDEN);


    }

}

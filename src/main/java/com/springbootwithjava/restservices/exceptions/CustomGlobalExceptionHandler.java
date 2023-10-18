package com.springbootwithjava.restservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    //MethodArgumentNotValidException

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "Not valid argument method in Global Exception Handling",
                ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

    }

    //HTTPRequestMethodNotSupportedException
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "Not valid argument method in Global Exception Handling- METHOD NOT ALLOWED",
                ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }


}

package com.springbootwithjava.restservices.exceptions;



//Now we are using global exception handler using rest controller advice for username not found instead controller advice


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex){

        return new CustomErrorDetails(new Date(), "From @Restcontrolleradvice not found ",
                ex.getMessage()
                );
    }

}

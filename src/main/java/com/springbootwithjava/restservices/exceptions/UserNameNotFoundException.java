package com.springbootwithjava.restservices.exceptions;

public class UserNameNotFoundException extends Exception {

    //We generate super class constructor when we inherit parent class
    //super class constructor
    public UserNameNotFoundException(String message){
        super(message);
    }




}

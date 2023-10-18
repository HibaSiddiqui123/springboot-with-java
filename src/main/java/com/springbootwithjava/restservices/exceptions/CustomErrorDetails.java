package com.springbootwithjava.restservices.exceptions;


import java.util.Date;

// Simple Custom error details bean
public class CustomErrorDetails {
    private Date timestamp;
    private String  message;
    private String errordetails;

    //FIELDS CONSTRUCTOR
    public CustomErrorDetails(Date timestamp, String message, String errordetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errordetails = errordetails;
    }

    // GETTERS
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrordetails() {
        return errordetails;
    }


}

package com.appdevelopersblog.ws.ProductsMicroservice.model;

import java.util.Date;

public class ErrorMessage {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorMessage() {

    }

    public ErrorMessage(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}

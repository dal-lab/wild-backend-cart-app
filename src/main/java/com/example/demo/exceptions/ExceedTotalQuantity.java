package com.example.demo.exceptions;

public class ExceedTotalQuantity extends RuntimeException {
    public ExceedTotalQuantity(String message){
        super(message);
    }
}

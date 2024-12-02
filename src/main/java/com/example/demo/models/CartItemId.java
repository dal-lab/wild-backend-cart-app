package com.example.demo.models;

public record CartItemId(
       String id
) {
    public CartItemId(){
        this(new GenerateId().id());
    }
}

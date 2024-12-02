package com.example.demo.models;

public record ProductId(
        String id
) {

    public ProductId() {
        this(new GenerateId().id());
    }

}

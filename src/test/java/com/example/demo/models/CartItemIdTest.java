package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CartItemIdTest {
    @Test
    void createCartItemId(){
        CartItemId id = new CartItemId();
        System.out.println("Generated ID: " + id.id());
        assertNotNull(id.id(), "ID should not be null");
    }
}

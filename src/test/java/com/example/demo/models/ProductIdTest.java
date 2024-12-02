package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductIdTest {
    @Test
    void createProductId(){
        ProductId id = new ProductId();
        System.out.println("Generated ID: " + id.id());
        assertNotNull(id.id(), "ID should not be null");
    }
    @Test
    void createMultipleCartItemIds_ShouldGenerateUniqueIds() {
        CartItemId id1 = new CartItemId();
        CartItemId id2 = new CartItemId();

        assertNotEquals(id1.id(), id2.id(), "Generated IDs should be unique");
    }

    @Test
    void generatedId_ShouldFollowExpectedFormat() {
        CartItemId id = new CartItemId();
        String generatedId = id.id();

        // ID 형식이 예상된 패턴을 따르는지 확인
        assertTrue(generatedId.matches("^[a-zA-Z0-9-]+$"), "ID should match expected format");
    }
}

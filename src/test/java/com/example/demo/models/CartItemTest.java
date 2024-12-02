package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartItemTest {

    private CartItem cartItem;
    private CartItemId cartItemId;
    private Product product;
    private ProductId productId;
    private ArrayList<Product> products;

    @BeforeEach
    void setUp() {
        productId = new ProductId();
        product = new Product(productId);

        cartItemId = new CartItemId();
        cartItem = new CartItem(cartItemId, product, 1);
    }

    @Test
    void addQuantity() {
        cartItem.addQuantity(1);
        assertEquals(2, cartItem.getQuantity());
    }
    @Test
    void addQuantity_WhenAddingPositiveNumber_ShouldIncreaseQuantity() {
        cartItem.addQuantity(1);
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void addQuantity_WhenAddingNegativeNumber_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> cartItem.addQuantity(-1));
    }

    @Test
    void addQuantity_WhenAddingZero_ShouldNotChangeQuantity() {
        cartItem.addQuantity(0);
        assertEquals(1, cartItem.getQuantity());
    }
}

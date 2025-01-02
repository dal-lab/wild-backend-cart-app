package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product("product-1", "Product #1", 5000);
        product2 = new Product("product-2", "Product #2", 10000);
    }


    @Test
    @DisplayName("getTotalPrice - Empty Cart")
    void getTotalPrice() {
        Cart cart = new Cart(List.of());

        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("getTotalPrice - Single LineItem")
    void getTotalPriceWithLineItem() {
        int quantity = 2;

        LineItem lineItem = createLineItem(product1, quantity);
        Cart cart = new Cart(List.of(lineItem));

        assertThat(cart.getTotalPrice()).isEqualTo(product1.getPrice() * quantity);
    }


    @Test
    @DisplayName("getTotalPrice - Multiple LineItems")
    void getTotalPriceWithLineItems() {
        int quantity1 = 2;
        int quantity2 = 3;

        Cart cart = new Cart(List.of(createLineItem(product1, quantity1), createLineItem(product2, quantity2)));


        assertThat(cart.getTotalPrice())
                .isEqualTo(
                        product1.getPrice() * quantity1 + product2.getPrice() * quantity2
                );
    }


    private LineItem createLineItem(Product product, int quantity) {
        LineItem lineItem = new LineItem(product.getId(), quantity);
        lineItem.setProduct(product);
        return lineItem;
    }
}

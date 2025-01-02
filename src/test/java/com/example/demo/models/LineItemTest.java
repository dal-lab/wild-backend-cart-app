package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LineItemTest {

    @Test
    void addQuantity() {
        String productId = "product-1";
        int quantity = 2;
        LineItem lineItem = new LineItem(productId, quantity);


        lineItem.addQuantity(3);


        assertThat(lineItem.getQuantity()).isEqualTo(5);
    }

    @Test
    void setProduct() {
        Product product = new Product("product-1", "Product #1", 5000);
        int quantity = 2;

        LineItem lineItem = new LineItem(product.getId(), quantity);

        lineItem.setProduct(product);

        assertThat(lineItem.getProductName()).isEqualTo(product.getName());
        assertThat(lineItem.getUnitPrice()).isEqualTo(product.getPrice());
        assertThat(lineItem.getTotalPrice()).isEqualTo(product.getPrice() * quantity);
    }

}

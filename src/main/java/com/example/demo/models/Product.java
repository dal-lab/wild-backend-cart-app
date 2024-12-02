package com.example.demo.models;

import java.util.Objects;

public record Product (
        ProductId productId
){

    @Override
    public String toString() {
        return "{" + productId + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}

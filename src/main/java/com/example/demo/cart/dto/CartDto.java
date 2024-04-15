package com.example.demo.cart.dto;

import java.util.List;

public record CartDto(
        List<LineItemDto> lineItems,
        int totalPrice
) {
    public record LineItemDto(
            String id,
            String productId,
            String productName,
            int unitPrice,
            int quantity,
            int totalPrice
    ) {
    }
}

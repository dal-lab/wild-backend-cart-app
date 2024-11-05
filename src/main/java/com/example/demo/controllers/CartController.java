package com.example.demo.controllers;

import com.example.demo.application.CartService;
import com.example.demo.controllers.dto.CartDto;
import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.mongodb.client.MongoDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    public CartController(
            LineItemDAO lineItemDAO,
            ProductDAO productDAO,
            MongoDatabase mongoDatabase
    ) {
    }


    @GetMapping
    CartDto detail() {
        Cart cart = cartService.getCart();
        return new CartDto(
                cart.getLineItems().stream().map(this::mapToDto).toList(),
                cart.getTotalPrice()
        );
    }

    private CartDto.LineItemDto mapToDto(LineItem lineItem) {
        return new CartDto.LineItemDto(
                lineItem.getId(),
                lineItem.getProductId(),
                lineItem.getProductName(),
                lineItem.getUnitPrice(),
                lineItem.getQuantity(),
                lineItem.getTotalPrice()
        );
    }
}

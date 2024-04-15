package com.example.demo.cart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cart.dto.CartDto;

@RestController
@RequestMapping("/cart/line-items")
public class LineItemController {
    @GetMapping
    CartDto list() {
        // TODO: Implements

        return new CartDto(
                List.of(
                        new CartDto.LineItemDto(
                                "line-item-1",
                                "product-1",
                                "Sticker",
                                3_000,
                                3,
                                9_000
                        )
                ),
                9_000
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create() {
        // TODO: Implements
    }
}

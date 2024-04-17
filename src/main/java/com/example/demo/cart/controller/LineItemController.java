package com.example.demo.cart.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.cart.dto.CartDto;

@RestController
@RequestMapping("/cart/line-items")
public class LineItemController {

    private final LineItemService lineItemService;

    @Autowired
    public LineItemController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @GetMapping
    public CartDto list() {
        List<LineItem> lineItems = lineItemService.getAllLineItems();
        List<CartDto.LineItemDto> lineItemDtos = lineItems.stream().map(lineItem ->
                new CartDto.LineItemDto(
                        lineItem.getId(),
                        lineItem.getProductId(),
                        lineItem.getProductName(),
                        lineItem.getUnitPrice(),
                        lineItem.getQuantity(),
                        lineItem.getTotalPrice()
                )
        ).collect(Collectors.toList());

        int totalPrice = lineItemDtos.stream().mapToInt(CartDto.LineItemDto::totalPrice).sum();

        return new CartDto(lineItemDtos, totalPrice);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CartDto.LineItemDto lineItemDto) {
        lineItemService.addOrUpdateItem(lineItemDto);
    }
}

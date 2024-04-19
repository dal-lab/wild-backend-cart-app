package com.example.demo.cart.controller;

import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {

    private final LineItemService lineItemService;

    @Autowired
    public BackdoorController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {

        List<LineItem> lineItems = Arrays.asList(
                new LineItem("product-1", "Sticker", 3000, 3, 9000),
                new LineItem("product-2", "Notebook", 5000, 2, 10000),
                new LineItem("product-3", "Pen", 1500, 10, 15000),
                new LineItem("product-4", "Eraser", 500, 5, 2500),
                new LineItem("product-5", "Ruler", 1000, 3, 3000)
        );
        lineItems.forEach(lineItemService::addItem);

        return "OK";
    }
}


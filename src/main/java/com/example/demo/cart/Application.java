package com.example.demo.cart;

import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.service.LineItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(LineItemService lineItemService) {
        return args -> {
            List<LineItem> lineItems = Arrays.asList(
                    new LineItem("product-1", "Sticker", 3000, 3, 9000),
                    new LineItem("product-2", "Notebook", 5000, 2, 10000),
                    new LineItem("product-3", "Pen", 1500, 10, 15000),
                    new LineItem("product-4", "Eraser", 500, 5, 2500),
                    new LineItem("product-5", "Ruler", 1000, 3, 3000)
            );
            lineItems.forEach(lineItemService::addItem);
        };
    }
}

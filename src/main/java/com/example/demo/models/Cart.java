package com.example.demo.models;

import java.util.Collections;
import java.util.List;

public class Cart {

    private List<LineItem> lineItems;
    private int totalPrice;

    public Cart(List<LineItem> lineItems, int totalPrice) {
        this.totalPrice = totalPrice;
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}

package com.example.demo.models;

public class LineItem {
    private String id;
    private String productId;
    private int quantity;

    private String productName;
    private int unitPrice;
    private int totalPrice;

    public LineItem(String id, String productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    /// Add the following methods to the LineItem class:
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

}

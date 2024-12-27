package com.example.demo.models;

public class LineItem {
    //Similar to DB schema
    private String id = null;
    private String productId;
    private int quantity;

    private String productName;
    private int unitPrice;
    private int totalPrice;

    public LineItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

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

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

}

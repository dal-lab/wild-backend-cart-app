package com.example.demo.cart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class LineItem {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productId;
    private String productName;
    private int unitPrice;
    private int quantity;
    private int totalPrice;

    // 기본 생성자 및 모든 필드를 포함한 생성자
    public LineItem() {}

    public LineItem(String productId, String productName, int unitPrice, int quantity, int totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
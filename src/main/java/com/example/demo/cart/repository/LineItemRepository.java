package com.example.demo.cart.repository;

import com.example.demo.cart.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    Optional<LineItem> findByProductId(String productId);
}


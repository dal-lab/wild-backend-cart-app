package com.example.demo.cart.service;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LineItemService {
    private final LineItemRepository lineItemRepository;

    @Autowired
    public LineItemService(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }
    public LineItem addItem(LineItem item) {
        return lineItemRepository.save(item);
    }

    public List<LineItem> getAllLineItems() {
        return lineItemRepository.findAll();
    }

    @Transactional
    public void addOrUpdateItem(CartDto.LineItemDto lineItemDto) {
        Optional<LineItem> existingItem = lineItemRepository.findByProductId(lineItemDto.productId());

        if (existingItem.isPresent()) {
            LineItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + lineItemDto.quantity());
            item.setTotalPrice(item.getUnitPrice() * item.getQuantity());
            lineItemRepository.save(item);
        } else {
            LineItem newItem = new LineItem(
                    lineItemDto.productId(),
                    lineItemDto.productName(),
                    lineItemDto.unitPrice(),
                    lineItemDto.quantity(),
                    lineItemDto.unitPrice() * lineItemDto.quantity()
            );
            lineItemRepository.save(newItem);
        }
    }

    // 모든 LineItem 삭제 (예: 전체 장바구니 비우기)
    @Transactional
    public void clearAllLineItems() {
        lineItemRepository.deleteAll();
    }
}

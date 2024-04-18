package com.example.demo.cart.service;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.repository.LineItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LineItemServiceTest {

    @Mock
    private LineItemRepository lineItemRepository;

    @InjectMocks
    private LineItemService lineItemService;

    @BeforeEach
    void setUp() {
        Mockito.reset(lineItemRepository);
    }

    @Test
    void testAddItem() {
        LineItem item = new LineItem("product-1", "Product 1", 100, 2, 200);
        when(lineItemRepository.save(any(LineItem.class))).thenReturn(item);

        LineItem savedItem = lineItemService.addItem(item);

        assertNotNull(savedItem);

        // TODO: 장바구니에 Line Item이 하나인 걸 확인합니다.
        assertEquals("Product 1", savedItem.getProductName());
        verify(lineItemRepository).save(item);
    }

    @Test
    void testGetAllLineItems() {
        LineItem item1 = new LineItem("product-1", "Product 1", 100, 2, 200);
        LineItem item2 = new LineItem("product-2", "Product 2", 150, 3, 450);
        when(lineItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<LineItem> items = lineItemService.getAllLineItems();
        assertNotNull(items);

        // TODO: 상품 수량이 2인 걸 확입합니다.
        assertEquals(2, items.size());
        verify(lineItemRepository).findAll();
    }

    @Test
    void testAddOrUpdateItem_NewItem() {
        CartDto.LineItemDto dto = new CartDto.LineItemDto(1L, "product-1", "Product 1", 100, 2, 200);
        when(lineItemRepository.findByProductId("product-1")).thenReturn(Optional.empty());
        when(lineItemRepository.save(any(LineItem.class))).thenReturn(new LineItem("product-1", "Product 1", 100, 2, 200));

        lineItemService.addOrUpdateItem(dto);

        verify(lineItemRepository).save(any(LineItem.class));
    }

    @Test
    void testAddOrUpdateItem_UpdateItem() {
        // TODO: 장바구니에 상품을 1개 담아둡니다.
        LineItem existingItem = new LineItem("product-1", "Product 1", 100, 1, 100);
        when(lineItemRepository.findByProductId("product-1")).thenReturn(Optional.of(existingItem));

        when(lineItemRepository.save(any(LineItem.class))).thenReturn(existingItem);

        CartDto.LineItemDto dto = new CartDto.LineItemDto(1L, "product-1", "Product 1", 100, 1, 100);
        lineItemService.addOrUpdateItem(dto);

        // TODO: 상품 수량이 2으로 늘어난 걸 확입합니다.
        verify(lineItemRepository).save(existingItem);
        assertEquals(2, existingItem.getQuantity());
        assertEquals(200, existingItem.getTotalPrice());
    }

    @Test
    void testClearAllLineItems() {
        lineItemService.clearAllLineItems();
        verify(lineItemRepository).deleteAll();
    }

}
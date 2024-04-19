package com.example.demo.cart.repository;

import com.example.demo.cart.model.LineItem;
import com.example.demo.cart.service.LineItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LineItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LineItemRepository lineItemRepository;

    @Test
    public void whenFindByProductId_thenReturnLineItem() {
        // given
        LineItem newItem = new LineItem("product-1", "Product One", 1000, 2, 2000);
        entityManager.persist(newItem);
        entityManager.flush();

        // when
        Optional<LineItem> foundItem = lineItemRepository.findByProductId(newItem.getProductId());

        // then
        assertTrue(foundItem.isPresent(), "Line item should be found with the given product ID");
        assertEquals(newItem.getProductId(), foundItem.get().getProductId(), "Product ID should match");
    }

    @Test
    public void whenFindByProductId_withNoMatching_thenReturnEmpty() {
        // when
        Optional<LineItem> foundItem = lineItemRepository.findByProductId("non-existent-id");

        // then
        assertFalse(foundItem.isPresent(), "Line item should not be found");
    }
}
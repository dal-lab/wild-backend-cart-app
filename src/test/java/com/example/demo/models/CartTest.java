package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.exceptions.ExceedTotalQuantity;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CartTest {

    private ProductId productId;
    private Cart cart;


    @DisplayName("기존의 상품을 추가할 경우")
    @Test
    void addCartItem() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when
        cart.addCartItem(productId, 1);

        // then
        assertEquals(2, cart.getTotalQuantity());
        assertEquals(1, cart.getCartList().size());
    }

    @DisplayName("새로운 상품을 추가할 경우")
    @Test
    void addNewCartItem() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when
        ProductId newProductId = new ProductId();
        cart.addCartItem(newProductId, 1);

        // then
        assertEquals(2, cart.getTotalQuantity());
        assertEquals(2, cart.getCartList().size());
    }

    @DisplayName("새로운 상품을 추가할 경우2")
    @Test
    void addNewCartItem2() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when
        ProductId newProductId = new ProductId();
        cart.addCartItem(newProductId, 2);

        ProductId newProductId2 = new ProductId();
        cart.addCartItem(newProductId2, 2);

        // then
        assertEquals(5, cart.getTotalQuantity());
        assertEquals(3, cart.getCartList().size());
    }

    @DisplayName("장바구니 총계가 20을 넘길 경우")
    @Test
    void checkTotalQuantityLimit() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when & then
        assertThrows(ExceedTotalQuantity.class, () -> {
                    cart.addCartItem(productId, 20);
                });
    }

    @DisplayName("장바구니 비우기")
    @Test
    void madeCartZero() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when
        cart.madeCartZero();

        // then
        assertEquals(0, cart.getTotalQuantity());
        assertEquals(0, cart.getCartList().size());
    }

    @DisplayName("장바구니 목록 보기")
    @Test
    void getCartList() {
        // given
        productId = new ProductId();
        cart = new Cart();
        cart.addCartItem(productId, 1);

        // when & then
        System.out.println(cart.getCartList().toString());
        assertEquals(cart.getCartList().getFirst().getProduct().productId(),
                productId);
        assertEquals(cart.getCartList().getFirst().getQuantity(), 1);
    }
}

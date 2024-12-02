package com.example.demo.models;

import com.example.demo.exceptions.ExceedTotalQuantity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private final List<CartItem> cartItems;
    private int totalQuantity;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalQuantity = 0;
    }

    public void checkTotalQuantity(int quantity) {
        if (this.totalQuantity + quantity > 20) {
            throw new ExceedTotalQuantity("장바구니 총 합계는 20을 넘길 수 없습니다.");
        }
    }

    public void addCartItem(ProductId productId, int quantity) {

        checkTotalQuantity(quantity);

        Optional<CartItem> existingCartItem = findCartItem(productId);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.addQuantity(quantity);
        } else {
            addNewCartItem(productId, quantity);
        }

        this.totalQuantity += quantity;

    }

    private void addNewCartItem(ProductId productId, int quantity) {
        if (productId == null) {
            throw new IllegalArgumentException("상품 ID는 null일 수 없습니다.");
        }
        CartItemId cartItemId = new CartItemId();
        Product product = new Product(productId);
        CartItem newCartItem = new CartItem(cartItemId, product, quantity);
        cartItems.add(newCartItem);
    }

    private Optional<CartItem> findCartItem(ProductId productId) {
        return cartItems.stream()
                .filter(cartItem -> {
                    Product product = cartItem.getProduct();
                    return product != null &&
                            product.productId().equals(productId);
                })
                .findFirst();
    }

    public List<CartItem> getCartList() {
        return cartItems;
    }

    public void madeCartZero() {
        this.totalQuantity = 0;
        this.cartItems.clear();
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}

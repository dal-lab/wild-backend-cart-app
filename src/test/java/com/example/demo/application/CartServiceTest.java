package com.example.demo.application;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class CartServiceTest {

    private Product product1;
    private Product product2;

    private List<LineItem> lineItems;

    private LineItemDAO lineItemDAO;
    private ProductDAO productDAO;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        product1 = new Product("product-1", "Product #1", 5000);
        product2 = new Product("product-2", "Product #2", 3000);

        lineItems = new ArrayList<>();

        lineItemDAO = mock(LineItemDAO.class);

        given(lineItemDAO.findAll()).willReturn(lineItems);

        productDAO = mock(ProductDAO.class);

        given(productDAO.find(product1.getId())).willReturn(product1);
        given(productDAO.find(product2.getId())).willReturn(product2);

        cartService = new CartService(lineItemDAO, productDAO);
    }

    @Test
    @DisplayName("장바구니가 비어 있으면 총 가격은 0이다.")
    void totalPriceIsZero() {
        // Given
        given(lineItemDAO.findAll()).willReturn(List.of());

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("장바구니가 있는 상풍 총 가격은 0이다.")
    void calculateTotalPriceWithOneLineItem() {
        // Given
        clearCart();

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("장바구니가 있는 상풍 총 가격은 0이다.")
    void calculateTotalPriceWithMultipleLineItems() {
        // Given
        int quantity1 = 2;
        int quantity2 = 2;
        addProductToCart(product1, quantity1);
        addProductToCart(product2, quantity2);

        // When
        Cart cart = cartService.getCart();

        // Then
        assertThat(cart.getTotalPrice()).isEqualTo(
                product1.getPrice() * quantity1 +
                        product2.getPrice() * quantity2
        );
    }

    @Test
    @DisplayName("비어있는 장바구니에 상품 담기")
    void addProductToEmptyCart() {
        // Given
        String productId = product1.getId();
        int quantity = 2;

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).add(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == quantity
        ));
    }

    @Test
    @DisplayName("장바구니에 이미 있는 상품 담기")
    void addExistingProductToCart() {
        // Given
        String productId = product1.getId();
        int quantity = 2;
        int oldQuantity = 3;
        lineItems.add(new LineItem(productId, oldQuantity));

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).update(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == oldQuantity + quantity
        ));
    }

    @Test
    @DisplayName("장바구니에 없는 상품 담기")
    void addNewProductToCart() {
        // Given
        String productId = product1.getId();
        int quantity = 2;


        lineItems.add(new LineItem(product2.getId(), 10));

        // When
        cartService.addProduct(productId, quantity);

        // Then
        verify(lineItemDAO).add(argThat(lineItem ->
                lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == quantity
        ));
    }

    private void clearCart() {
        lineItems.clear();
    }

    private void addProductToCart(Product product, int quantity) {
        String id = "item-" + (lineItems.size() + 1);
        LineItem lineItem = new LineItem(id, product.getId(), quantity);
        lineItems.add(lineItem);
    }
}

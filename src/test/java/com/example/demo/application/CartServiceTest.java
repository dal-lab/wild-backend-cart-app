package com.example.demo.application;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CartServiceTest {
    LineItemDAO lineItemDAO;
    ProductDAO productDAO;
    CartService cartService;

    //
    private Product product1;
    private Product product2;


    @BeforeEach
    void setUp() {
        lineItemDAO = mock(LineItemDAO.class);
        productDAO = mock(ProductDAO.class);
        product1 = new Product("1", "product1", 1000);
        product2 = new Product("2", "product2", 2000);

        addProductToCart(product1, 1);
        addProductToCart(product2, 2);

        given(productDAO.find(product1.getId())).willReturn(product1);
        given(productDAO.find(product2.getId())).willReturn(product2);


        cartService = new CartService(lineItemDAO, productDAO);
    }

    @Test
    @DisplayName("getCart returns a cart with total price 0 when there are no line items")
    void totalPriceIsZero() {

        
        // print all properties of lineItemDAO
        clearCart();

        System.out.println("productDAO: " + productDAO);

        System.out.println("cartService: " + cartService.toString());

        Cart cart = cartService.getCart();

        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("getCart returns a cart with line items")
    void calculateTotalPrice() {
        Cart cart = cartService.getCart();
        assertThat(cart.getTotalPrice()).isEqualTo(
                product1.getPrice() + product2.getPrice() * 2

        );

    }

    @AfterEach
    void tearDown() {
        clearCart();
    }


    void clearCart() {
        given(lineItemDAO.findAll()).willReturn(List.of());
    }

    void addProductToCart(Product product, int quantity) {

        List<LineItem> lineItems = lineItemDAO.findAll();
        LineItem lineItem = new LineItem("ListItemId-" + (lineItemDAO.findAll().size() + 1), product.getId(), quantity);
        lineItem.setTotalPrice(product.getPrice() * quantity);
        lineItems.add(lineItem);
        given(lineItemDAO.findAll()).willReturn(lineItems);
    }

}

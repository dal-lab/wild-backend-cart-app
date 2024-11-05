package com.example.demo.controllers;

import com.example.demo.application.CartService;
import com.example.demo.models.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartService cartService;

    @Test
    @DisplayName("GET /cart")
    void detail() throws Exception {
        Cart cart = new Cart(List.of(), 0);
        given(cartService.getCart()).willReturn(cart);
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("TEST")));
    }
}

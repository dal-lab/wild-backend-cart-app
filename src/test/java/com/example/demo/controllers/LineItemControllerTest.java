package com.example.demo.controllers;

import com.example.demo.application.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(LineItemController.class)
class LineItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    @DisplayName("POST /cart/line-items")
    void addProduct() throws Exception {
        String json = """
                {
                    "productId": "product-1",
                    "quantity": 2
                }
                """;

        mockMvc.perform(post("/cart/line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /cart/line-items - Without Product ID")
    void addProductWithoutProductId() throws Exception {
        String json = """
                {
                    "productId": "     ",
                    "quantity": 2
                }
                """;

        mockMvc.perform(post("/cart/line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

}

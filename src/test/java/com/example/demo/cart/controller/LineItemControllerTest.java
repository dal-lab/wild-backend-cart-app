package com.example.demo.cart.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LineItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // TODO: 장바구니를 올바르게 처리하면 `@Disabled` 애너테이션을 지우고,
    //       이 테스트가 돌아가게 합니다.
    @Disabled
    @Test
    @DisplayName("GET /cart/line-items - 장바구니가 비어있을 때")
    void testListWithNoLineItem() throws Exception {
        // TODO: 장바구니를 비웁니다.

        mockMvc.perform(get("/cart/line-items"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"lineItems\":[]")
                ));
    }

    @Test
    @DisplayName("GET /cart/line-items - 장바구니에 상품이 있을 때")
    void testListWithLineItems() throws Exception {
        // TODO: 장바구니에 상품을 담아 둡니다.

        mockMvc.perform(get("/cart/line-items"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"lineItems\":[{")
                ));
    }

    @Test
    @DisplayName("POST /cart/line-items - 장바구니에 처음으로 삼품을 담을 때")
    void testAddNewItem() throws Exception {
        // TODO: 장바구니를 비웁니다.

        String json = """
                {
                    "productId": "product-1",
                    "quantity: 2
                }
                """;

        mockMvc.perform(post("/cart/line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        // TODO: 장바구니에 Line Item이 하나인 걸 확인합니다.
        // TODO: 상품 수량이 2인 걸 확입합니다.
    }

    @Test
    @DisplayName("POST /cart/line-items - 장바구니에 같은 상품을 담을 때")
    void testAddSameItem() throws Exception {
        // TODO: 장바구니에 상품을 1개 담아둡니다.

        String json = """
                {
                    "productId": "product-1",
                    "quantity: 2
                }
                """;

        mockMvc.perform(post("/cart/line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        // TODO: 장바구니에 Line Item이 여전히 하나인 걸 확인합니다.
        // TODO: 상품 수량이 3으로 늘어난 걸 확입합니다.
    }

    @Test
    @DisplayName("POST /cart/line-items - 장바구니에 다른 상품을 담을 때")
    void testAddDifferentItem() throws Exception {
        // TODO: 장바구니에 상품을 1개 담아둡니다.

        String json = """
                {
                    "productId": "product-2",
                    "quantity: 2
                }
                """;

        mockMvc.perform(post("/cart/line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        // TODO: 장바구니에 Line Item이 2개로 늘어난 걸 확인합니다.
        // TODO: 상품 수량이 각각 1개, 2개인 걸 확인합니다.
    }
}

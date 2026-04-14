package com.example.productvalidator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAcceptValidProduct() throws Exception {
        String requestBody = """
                {
                  "sku": "SKU-12345678",
                  "productName": "Mechanical Keyboard",
                  "quantityInStock": 5,
                  "price": 199.99,
                  "category": "Electronics"
                }
                """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldRejectInvalidProduct() throws Exception {
        String requestBody = """
                {
                  "sku": "123",
                  "productName": "",
                  "quantityInStock": -2,
                  "price": 0,
                  "category": "Toys"
                }
                """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        [
                          "The sku must be in the format SKU-XXXXXXXX",
                          "The productName is a mandatory field",
                          "The quantityInStock cannot be negative",
                          "The price must be greater than zero",
                          "Invalid product category"
                        ]
                        """));
    }

    @Test
    void shouldRejectMalformedJson() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        ["Malformed JSON request body"]
                        """));
    }
}

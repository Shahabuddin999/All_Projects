package com.product;
import com.product.controller.ProductController;
import com.product.dto.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void resetProductList() throws Exception {
        Field listField = ProductController.class.getDeclaredField("productList");
        listField.setAccessible(true);
        List<Product> freshList = new ArrayList<>();
        freshList.add(new Product(1, "Chair", 30, 1500));  // ID = 1
        freshList.add(new Product(2, "Table", 15, 6000));  // ID = 2
        listField.set(null, freshList);
    }

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/product"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    void testCreateNewProduct() throws Exception {
        Product p = new Product(0, "Fan", 10, 3000);

        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Fan")));
    }

    @Test
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/product/9999"))  // Non-existent ID
            .andExpect(status().isOk())
            .andExpect(content().string(""));  // Returns null, so empty body
    }

    @Test
    void testUpdateExistingProduct() throws Exception {
        Product updated = new Product(0, "UpdatedChair", 100, 5000.0);

        mockMvc.perform(put("/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("UpdatedChair")))
            .andExpect(jsonPath("$.price", is(5000.0)))
            .andExpect(jsonPath("$.quantity", is(100)));
    }

    @Test
    void testDeleteProduct() throws Exception {
    	   mockMvc.perform(delete("/product/1"))
           .andExpect(status().isOk())
           .andExpect(content().string("true"));
    }

}

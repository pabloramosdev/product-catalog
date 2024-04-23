package com.pabloramosdev.product.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Get Price for ZARA product 35455 at 2020-06-14 10:00:00")
    void getPrices() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-14 10:00:00&product_id=35455&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.fare").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Get Price for ZARA product 35455 at 2020-06-14 16:00:00")
    void getPrices2() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-14 16:00:00&product_id=35455&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.fare").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Get Price for ZARA product 35455 at 2020-06-14 21:00:00")
    void getPrices3() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-14 21:00:00&product_id=35455&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.fare").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Get Price for ZARA product 35455 at 2020-06-15 10:00:00")
    void getPrices4() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-15 10:00:00&product_id=35455&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.fare").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Get Price for ZARA product 35455 at 2020-06-16 21:00:00")
    void getPrices5() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-16 21:00:00&product_id=35455&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.fare").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    @DisplayName("Get Price for ZARA product 40000 at 2020-06-16 21:00:00")
    void getPrices6() throws Exception {
        mvc.perform(get("/prices?fare_date=2020-06-16 21:00:00&product_id=40000&brand_id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
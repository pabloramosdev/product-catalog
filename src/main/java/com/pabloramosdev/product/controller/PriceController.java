package com.pabloramosdev.product.controller;

import com.pabloramosdev.product.model.PriceDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

@RestController
@RequestMapping(path = "/prices")
public class PriceController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrices() {
        return ResponseEntity.ok().body(PriceDto.builder()
                        .brandId(1)
                        .productId(35455)
                        .fare(1)
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plus(Period.ofMonths(1)))
                        .price(new BigDecimal("35.50"))
                .build());
    }

}

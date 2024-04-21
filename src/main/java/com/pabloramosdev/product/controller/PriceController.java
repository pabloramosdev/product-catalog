package com.pabloramosdev.product.controller;

import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.persistence.entity.Price;
import com.pabloramosdev.product.persistence.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/prices")
public class PriceController {

    private final PriceRepository priceRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrices(@RequestParam("fare_date") LocalDateTime fareDate,
                                              @RequestParam("product_id") Integer productId,
                                              @RequestParam("brand_id") Integer brandId) {
        Price price = priceRepository.findProductPriceByBrandAndDateRange(fareDate, productId, brandId);
        return ResponseEntity.ok().body(PriceDto.builder()
                        .brandId(price.getBrandId())
                        .productId(price.getProductId())
                        .fare(price.getPriceList())
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate())
                        .price(price.getPrice())
                .build());
    }

}

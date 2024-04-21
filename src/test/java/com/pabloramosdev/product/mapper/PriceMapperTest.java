package com.pabloramosdev.product.mapper;

import com.pabloramosdev.product.entity.Price;
import com.pabloramosdev.product.model.PriceDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceMapperTest {

    private PriceMapper priceMapper = new PriceMapper();

    @Test
    void entityToDto() {
        Price price = createPrice();
        PriceDto priceDto = priceMapper.entityToDto(price);
        assertEquals(35455, priceDto.getProductId());
        assertEquals(1, priceDto.getBrandId());
        assertEquals(1, priceDto.getFare());
        assertEquals(LocalDateTime.of(2020, 6, 14,10, 0, 0), priceDto.getStartDate());
        assertEquals(LocalDateTime.of(2020, 7, 14,10, 0, 0), priceDto.getEndDate());
        assertEquals(new BigDecimal("50.00"), priceDto.getPrice());
    }

    private Price createPrice() {
        Price price = new Price();
        price.setId(1);
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14,10, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 7, 14,10, 0, 0));
        price.setPriority(1);
        price.setPrice(new BigDecimal("50.00"));
        price.setCurrency("EUR");
        return price;
    }
}
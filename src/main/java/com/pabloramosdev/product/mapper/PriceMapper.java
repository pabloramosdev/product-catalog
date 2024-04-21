package com.pabloramosdev.product.mapper;

import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.entity.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public PriceDto entityToDto(Price price) {
        return PriceDto.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .fare(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .build();
    }

}

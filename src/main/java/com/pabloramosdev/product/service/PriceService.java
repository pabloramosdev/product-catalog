package com.pabloramosdev.product.service;

import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.persistence.entity.Price;
import com.pabloramosdev.product.persistence.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceDto productPriceWithHighestPriority(LocalDateTime fareDate, Integer productId, Integer brandId) {
        Price price = priceRepository.findProductPriceByBrandAndDateRange(fareDate, productId, brandId)
                .orElseGet(Price::new);
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

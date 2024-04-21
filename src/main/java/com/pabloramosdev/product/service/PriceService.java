package com.pabloramosdev.product.service;

import com.pabloramosdev.product.mapper.PriceMapper;
import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PriceDto productPriceWithHighestPriority(LocalDateTime fareDate, Integer productId, Integer brandId) {
        return priceRepository.findProductPriceByBrandAndDateRange(fareDate, productId, brandId)
                .map(priceMapper::entityToDto)
                .orElseGet(() -> PriceDto.builder().build());
    }
}

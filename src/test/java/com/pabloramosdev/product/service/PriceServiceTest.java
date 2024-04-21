package com.pabloramosdev.product.service;

import com.pabloramosdev.product.entity.Price;
import com.pabloramosdev.product.mapper.PriceMapper;
import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock PriceRepository priceRepository;

    @Mock PriceMapper priceMapper;

    @InjectMocks PriceService priceService;

    @Test
    void productPriceWithHighestPriorityWhenLookForAnExistingPriceThenReturnPrioritizedPrice() {
        when(priceRepository.findProductPriceByBrandAndDateRange(any(), anyInt(), anyInt())).thenReturn(Optional.of(new Price()));
        when(priceMapper.entityToDto(any())).thenReturn(PriceDto.builder().build());
        PriceDto priceDto = priceService.productPriceWithHighestPriority(LocalDateTime.now(), 1, 1);
        verify(priceRepository).findProductPriceByBrandAndDateRange(any(), anyInt(), anyInt());
        verify(priceMapper).entityToDto(any());
        assertNotNull(priceDto);
    }

    @Test
    void productPriceWithHighestPriorityWhenLookForAnNonExistingPriceThenReturnEmptyPrice() {
        when(priceRepository.findProductPriceByBrandAndDateRange(any(), anyInt(), anyInt())).thenReturn(Optional.empty());
        PriceDto priceDto = priceService.productPriceWithHighestPriority(LocalDateTime.now(), 1, 1);
        verify(priceRepository).findProductPriceByBrandAndDateRange(any(), anyInt(), anyInt());
        assertNotNull(priceDto);
        assertNull(priceDto.getProductId());
        assertNull(priceDto.getBrandId());
        assertNull(priceDto.getPrice());
        assertNull(priceDto.getStartDate());
        assertNull(priceDto.getEndDate());
        assertNull(priceDto.getFare());
    }

}
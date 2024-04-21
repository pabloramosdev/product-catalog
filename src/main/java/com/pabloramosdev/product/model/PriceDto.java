package com.pabloramosdev.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PriceDto {
    private Integer productId;
    private Integer brandId;
    private Integer fare;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}

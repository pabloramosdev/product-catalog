package com.pabloramosdev.product.persistence.repository;

import com.pabloramosdev.product.persistence.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("select p from Price p where p.productId = :productId and p.brandId = :brandId and p.startDate <= :date and p.endDate >= :date order by p.priority desc limit 1")
    Price findProductPriceByBrandAndDateRange(@Param("date") LocalDateTime applicationDate,
                                              @Param("productId") Integer productId,
                                              @Param("brandId") Integer brandId);
}

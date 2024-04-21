package com.pabloramosdev.product.repository;

import com.pabloramosdev.product.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("select p from Price p where p.productId = :productId and p.brandId = :brandId and p.startDate <= :date and p.endDate >= :date order by p.priority desc limit 1")
    Optional<Price> findProductPriceByBrandAndDateRange(@Param("date") LocalDateTime applicationDate,
                                                        @Param("productId") Integer productId,
                                                        @Param("brandId") Integer brandId);
}

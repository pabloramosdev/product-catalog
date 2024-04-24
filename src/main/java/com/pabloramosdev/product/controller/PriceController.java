package com.pabloramosdev.product.controller;

import com.pabloramosdev.product.model.PriceDto;
import com.pabloramosdev.product.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final PriceService priceService;

    @Operation(summary = "Retrieve the best price for a product in a range of dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The best Price",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceDto.class)) }),
            @ApiResponse(responseCode = "204", description = "There is not price for provided parameters",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad parameters supplied",
                    content = @Content)})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrices(@RequestParam("fare_date")
                                              @Parameter(description = "Application date") LocalDateTime fareDate,
                                              @RequestParam("product_id")
                                              @Parameter(description = "Id of the product") Integer productId,
                                              @RequestParam("brand_id")
                                              @Parameter(description = "Id of the brand") Integer brandId) {
        return ResponseEntity.ok(priceService.productPriceWithHighestPriority(fareDate, productId, brandId));
    }

}

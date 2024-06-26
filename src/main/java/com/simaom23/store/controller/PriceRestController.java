package com.simaom23.store.controller;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simaom23.store.dto.ResponseDTO;
import com.simaom23.store.service.PriceService;

@RestController
public class PriceRestController {

    private final PriceService priceService;

    public PriceRestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/check-price")
    @Cacheable(value = "pricesCache")
    public ResponseEntity<Object> getPrices(@RequestParam int product_id,
            @RequestParam int brand_id, @RequestParam String date) {
        Optional<ResponseDTO> response = priceService.checkPrice(product_id, brand_id, date);

        return response.isPresent() ? ResponseEntity.ok(response.get()) : ResponseEntity.ok().build();
    }
}

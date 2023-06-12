package com.simaom23.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simaom23.store.model.Response;
import com.simaom23.store.service.PriceService;

@RestController
public class PriceRestController {

    @Autowired
    PriceService priceService;

    /* Mapping for service */
    @GetMapping("/getPrices")
    public Response getPrices(@RequestParam int product_id,
            @RequestParam int brand_id, @RequestParam String date) {
        return priceService.checkProduct(product_id, brand_id, date);
    }
}

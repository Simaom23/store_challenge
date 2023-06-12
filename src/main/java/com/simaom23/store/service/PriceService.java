package com.simaom23.store.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simaom23.store.model.Price;
import com.simaom23.store.model.Response;
import com.simaom23.store.repository.PriceRepository;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Response checkProduct(int product_id, int brand_id, String date) {

        /* Converts String to Timestamp SQL */
        Timestamp date_conv;
        try {
            date_conv = Timestamp.valueOf(date);
        } catch (Exception e) {
            return new Response();
        }

        List<Price> results = priceRepository.findAllEntries(product_id, brand_id, date_conv); // Calls query to fetch
                                                                                               // entries from H2 DB

        if (results.size() == 0) // Check if query returned nothing
            return new Response();

        /* Calculates discount tax */
        BigDecimal highest_priority = results.get(0).getPrice();
        float discount = 0;
        if (results.size() > 1) {
            float lowest_priority = results.get(1).getPrice().floatValue();
            discount = 100 - ((highest_priority.floatValue() / lowest_priority) * 100);
        }

        /*
         * Fetches the values from list index 0
         * to create Response object
         */
        String start_date = results.get(0).getStart_date().toString();
        String end_date = results.get(0).getEnd_date().toString();
        String curr = results.get(0).getCurr();

        return new Response(product_id, brand_id, discount, start_date, end_date, highest_priority, curr);
    }
}

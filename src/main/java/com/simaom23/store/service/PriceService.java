package com.simaom23.store.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simaom23.store.model.Price;
import com.simaom23.store.model.Response;
import com.simaom23.store.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Response> checkPrice(int productId, int brandId, String dateString) {
        Timestamp date = convertToDate(dateString);
        List<Price> results = priceRepository.findAllEntries(productId, brandId, date);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        BigDecimal highestPrice = results.get(0).getPrice();
        float discount = calculateDiscount(results, highestPrice);

        // Fetch values from the first result to create Response object
        String startDate = results.get(0).getStart_date().toString();
        String endDate = results.get(0).getEnd_date().toString();
        String currency = results.get(0).getCurr();
        Response response = new Response(productId, brandId, discount, startDate, endDate, highestPrice, currency);

        return Optional.ofNullable(response);
    }

    // Convert Date String to Timestamp SQL
    private Timestamp convertToDate(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return Timestamp.valueOf(localDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date must be in the following format: YYYY-MM-DDThh:mm:ss");
        }
    }

    // Calculate discount percentage
    private float calculateDiscount(List<Price> results, BigDecimal highestPrice) {
        if (results.size() > 1) {
            BigDecimal lowestPrice = results.get(1).getPrice();
            float discountPercentage = 100 - (highestPrice.floatValue() / lowestPrice.floatValue() * 100);
            return discountPercentage;
        }
        return 0;
    }
}

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
        Timestamp date = convertToTimestamp(dateString);
        List<Price> results = priceRepository.findAllEntries(productId, brandId, date);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        BigDecimal highestPriorityPrice = results.get(0).getPrice();
        String startDate = results.get(0).getStart_date().toString();
        String endDate = results.get(0).getEnd_date().toString();
        String currency = results.get(0).getCurr();

        // Exclude first from beeing sorted
        results.remove(0);
        float discount = calculateDiscount(results, highestPriorityPrice);

        Response response = new Response(productId, brandId, discount, startDate, endDate, highestPriorityPrice,
                currency);

        return Optional.of(response);
    }

    // Convert Date String to Timestamp SQL
    private Timestamp convertToTimestamp(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return Timestamp.valueOf(localDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date must be in the following format: YYYY-MM-DDThh:mm:ss");
        }
    }

    // Calculate discount percentage
    private float calculateDiscount(List<Price> results, BigDecimal highestPriority) {
        if (results.size() > 0) {
            // Array stream to get a sorted array by ascending price and extract the first
            // Optional value from the sorted array
            BigDecimal lowestPrice = results.stream().map(p -> p.getPrice()).sorted().findFirst().get();
            float discountPercentage = 100.0f - (highestPriority.floatValue() / lowestPrice.floatValue() * 100.0f);
            return discountPercentage;
        }
        return 0;
    }
}

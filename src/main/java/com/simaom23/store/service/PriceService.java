package com.simaom23.store.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simaom23.store.dto.ResponseDTO;
import com.simaom23.store.mapper.PriceMapper;
import com.simaom23.store.model.Price;
import com.simaom23.store.repository.PriceRepository;
import com.simaom23.store.util.TimestampUtil;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<ResponseDTO> checkPrice(int productId, int brandId, String dateString) {
        Timestamp date = TimestampUtil.convertToTimestamp(dateString);
        List<Price> results = priceRepository.findAllEntries(productId, brandId, date);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        Price highestPriorityPrice = results.get(0);

        // Exclude first from beeing sorted
        results.remove(0);
        float discount = calculateDiscount(results, highestPriorityPrice.getPrice());

        ResponseDTO response = PriceMapper.mapToResponseDTO(highestPriorityPrice, discount);
        return Optional.of(response);
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

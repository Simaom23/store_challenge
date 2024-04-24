package com.simaom23.store.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private int product_id;
    private int brand_id;
    private float discount_percentage;
    private String start_date;
    private String end_date;
    private BigDecimal price;
    private String curr;
}

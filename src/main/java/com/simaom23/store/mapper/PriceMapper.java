package com.simaom23.store.mapper;

import com.simaom23.store.model.Price;
import com.simaom23.store.util.TimestampUtil;
import com.simaom23.store.dto.ResponseDTO;

public class PriceMapper {

    public static ResponseDTO mapToResponseDTO(Price price, float discount) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setProduct_id(price.getProduct_id());
        responseDTO.setBrand_id(price.getBrand_id());
        responseDTO.setStart_date(TimestampUtil.convertToString(price.getStart_date()));
        responseDTO.setEnd_date(TimestampUtil.convertToString(price.getEnd_date()));
        responseDTO.setPrice(price.getPrice());
        responseDTO.setCurr(price.getCurr());
        responseDTO.setDiscount_percentage(discount);

        return responseDTO;
    }
}

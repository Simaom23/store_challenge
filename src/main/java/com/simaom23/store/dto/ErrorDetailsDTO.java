package com.simaom23.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDTO {

    private String timestamp;
    private int status;
    private String message;
    private String path;

}
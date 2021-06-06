package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponse {
    private Long id;
    private PharmacyResponse pharmacy;
    private LocalDate deadLine;
    private PharmacistResponse pharmacist;
}

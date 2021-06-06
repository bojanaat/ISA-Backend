package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferResponse {
    private Long id;
    private int totalPrice;
    private LocalDate deadLine;
    private OrderResponse orderResponse;
    private SupplierResponse supplier;
}

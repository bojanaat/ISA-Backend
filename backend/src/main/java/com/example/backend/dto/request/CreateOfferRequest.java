package com.example.backend.dto.request;


import lombok.Data;

@Data
public class CreateOfferRequest {
    private int totalPrice;
    private String deadLine;
    private Long orderId;
    private Long supplierId;
}

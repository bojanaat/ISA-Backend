package com.example.backend.dto.response;

import lombok.Data;

@Data
public class MedicineOrderResponse {
    private Long id;
    private int quantity;
    private MedicineResponse medicineResponse;
    private OrderResponse orderResponse;

}

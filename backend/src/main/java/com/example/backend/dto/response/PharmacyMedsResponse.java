package com.example.backend.dto.response;

import lombok.Data;

@Data
public class PharmacyMedsResponse {
    private Long id;
    private MedicineResponse medicineResponse;
    private PharmacyResponse pharmacyResponse;
    private int quantity;
}

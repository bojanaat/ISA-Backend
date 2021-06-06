package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShiftPharmacistResponse {
    private Long id;
    private LocalTime startShift;
    private LocalTime endShift;
    private PharmacyResponse pharmacyResponse;
    private PharmacistResponse pharmacistResponse;
}

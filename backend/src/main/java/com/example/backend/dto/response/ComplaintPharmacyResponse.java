package com.example.backend.dto.response;

import lombok.Data;

@Data
public class ComplaintPharmacyResponse {
    private Long id;
    private PatientResponse patientResponse;
    private String text;
    private boolean answered;
    private PharmacyResponse pharmacyResponse;
}

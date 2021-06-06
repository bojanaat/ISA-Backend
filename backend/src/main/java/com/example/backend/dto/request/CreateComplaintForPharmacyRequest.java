package com.example.backend.dto.request;

import lombok.Data;

@Data
public class CreateComplaintForPharmacyRequest {
    private String text;
    private Long pharmacyId;
    private Long patientId;
}

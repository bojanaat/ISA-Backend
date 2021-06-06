package com.example.backend.dto.request;

import lombok.Data;

@Data
public class CreateComplaintForPharmacistRequest {
    private String text;
    private Long pharmacistId;
    private Long pharmacyId;
    private Long patientId;
}

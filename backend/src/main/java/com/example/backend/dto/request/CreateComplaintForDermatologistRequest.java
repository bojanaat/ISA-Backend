package com.example.backend.dto.request;

import lombok.Data;

@Data
public class CreateComplaintForDermatologistRequest {

    private String text;
    private Long dermatologistId;
    private Long pharmacyId;
    private Long patientId;
}

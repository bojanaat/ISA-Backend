package com.example.backend.dto.request;

import lombok.Data;

@Data
public class CreateComplaintForMedicineRequest {
    private String text;
    private Long medicineId;
    private Long pharmacyId;
    private Long patientId;
}

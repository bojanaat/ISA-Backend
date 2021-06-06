package com.example.backend.dto.response;

import lombok.Data;

@Data
public class ComplaintMedicineResponse {
    private Long id;
    private PatientResponse patientResponse;
    private String text;
    private boolean answered;
    private MedicineResponse medicineResponse;
}

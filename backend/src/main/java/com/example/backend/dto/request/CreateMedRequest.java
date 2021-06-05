package com.example.backend.dto.request;

import com.example.backend.utils.MedShape;
import com.example.backend.utils.MedicineType;
import lombok.Data;

@Data
public class CreateMedRequest {
    private String name;
    private String code;
    private String medShape;
    private String medicineType;
    private String ingredients;
    private String manufacturer;
    private boolean recipe;
    private String replacementCode;
    private String notes;
}

package com.example.backend.dto.response;

import com.example.backend.utils.MedShape;
import com.example.backend.utils.MedicineType;
import lombok.Data;

@Data
public class MedicineResponse {

    private Long id;
    private String name;
    private String code;
    private MedShape medShape;
    private MedicineType medicineType;
    private String ingredients;
    private String manufacturer;
    private boolean recipe;
    private String replacementCode;
    private String notes;
}

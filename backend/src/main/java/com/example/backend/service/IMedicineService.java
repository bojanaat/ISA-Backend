package com.example.backend.service;

import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacistResponse;

public interface IMedicineService {
    MedicineResponse createMed(CreateMedRequest request);
}

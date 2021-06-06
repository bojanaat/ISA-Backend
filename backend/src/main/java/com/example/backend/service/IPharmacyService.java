package com.example.backend.service;

import com.example.backend.dto.request.PharmacyRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacyResponse;

import java.util.List;

public interface IPharmacyService {
    PharmacyResponse createPharmacy(PharmacyRequest request) throws Exception;

    List<PharmacyResponse> getAllPharmacies();

    List<PharmacyResponse> getSubscribedPharmacies(Long id);
}

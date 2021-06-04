package com.example.backend.service;

import com.example.backend.dto.request.PharmacyRequest;
import com.example.backend.dto.response.PharmacyResponse;

public interface IPharmacyService {
    PharmacyResponse createPharmacy(PharmacyRequest request) throws Exception;
}

package com.example.backend.service;

import com.example.backend.dto.request.PharmacyAdminRequest;
import com.example.backend.dto.response.PharmacyAdminResponse;

public interface IPharmacyAdminService {
    PharmacyAdminResponse createPharmacyAdmin(PharmacyAdminRequest request) throws Exception;
}

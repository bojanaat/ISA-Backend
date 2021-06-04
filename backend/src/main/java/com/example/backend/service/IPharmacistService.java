package com.example.backend.service;

import com.example.backend.dto.request.PharmacistRequest;
import com.example.backend.dto.response.PharmacistResponse;

public interface IPharmacistService {
    PharmacistResponse createPharmacist(PharmacistRequest request) throws Exception;
}

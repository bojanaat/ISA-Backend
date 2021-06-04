package com.example.backend.service;

import com.example.backend.dto.request.DermatologistRequest;
import com.example.backend.dto.request.PharmacistRequest;
import com.example.backend.dto.response.DermatologistResponse;
import com.example.backend.dto.response.PharmacistResponse;

public interface IDermatologistService {
    DermatologistResponse createDermatologist(DermatologistRequest request) throws Exception;

}

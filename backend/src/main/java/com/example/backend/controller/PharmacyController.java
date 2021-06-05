package com.example.backend.controller;

import com.example.backend.dto.request.PharmacyRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacyResponse;
import com.example.backend.service.IPharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final IPharmacyService _iPharmacyService;

    public PharmacyController(IPharmacyService iPharmacyService) {
        _iPharmacyService = iPharmacyService;
    }

    @PostMapping()
    public PharmacyResponse createPharmacy(@RequestBody PharmacyRequest request) throws Exception {
        return _iPharmacyService.createPharmacy(request);
    }

    @GetMapping()
    public List<PharmacyResponse> getAllPharmacies(){
        return _iPharmacyService.getAllPharmacies();
    }
}

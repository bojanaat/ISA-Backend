package com.example.backend.controller;

import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacistResponse;
import com.example.backend.service.IMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meds")
public class MedicineController {

    private final IMedicineService _iMedicineService;

    public MedicineController(IMedicineService iMedicineService) {
        _iMedicineService = iMedicineService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public MedicineResponse createMed(@RequestBody CreateMedRequest request) throws Exception {
        return _iMedicineService.createMed(request);
    }
}

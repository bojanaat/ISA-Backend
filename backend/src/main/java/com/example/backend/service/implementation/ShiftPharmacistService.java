package com.example.backend.service.implementation;

import com.example.backend.dto.response.ShiftPharmacistResponse;
import com.example.backend.model.ShiftPharmacist;
import com.example.backend.repository.IShiftPharmacistRepository;
import com.example.backend.service.IShiftPharmacistService;
import org.springframework.stereotype.Service;

@Service
public class ShiftPharmacistService implements IShiftPharmacistService {
    private final IShiftPharmacistRepository _shiftPharmacistRepository;
    private final PharmacyService _pharmacyService;
    private final PharmacistService _pharmacistService;

    public ShiftPharmacistService(IShiftPharmacistRepository shiftPharmacistRepository, PharmacyService pharmacyService, PharmacistService pharmacistService) {
        _shiftPharmacistRepository = shiftPharmacistRepository;
        _pharmacyService = pharmacyService;
        _pharmacistService = pharmacistService;
    }


    public ShiftPharmacistResponse mapToShiftPharmacistResponse(ShiftPharmacist shiftPharmacist) {
        ShiftPharmacistResponse shiftResponse = new ShiftPharmacistResponse();
        shiftResponse.setId(shiftPharmacist.getId());
        shiftResponse.setStartShift(shiftPharmacist.getStartShift());
        shiftResponse.setEndShift(shiftPharmacist.getEndShift());
        shiftResponse.setPharmacyResponse(_pharmacyService.mapToResponse(shiftPharmacist.getPharmacy()));
        shiftResponse.setPharmacistResponse(_pharmacistService.mapToResponse(shiftPharmacist.getPharmacist()));
        return shiftResponse;
    }
}

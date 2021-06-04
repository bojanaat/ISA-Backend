package com.example.backend.controller;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.*;
import com.example.backend.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IPatientService _iPatientService;

    private final ISystemAdminService _iSystemAdminService;

    private final ISupplierService _iSupplierService;

    private final IPharmacyAdminService _iPharmacyAdminService;

    private final IDermatologistService _iDermatologistService;

    private final IAuthService _iAuthService;

    private final IPharmacistService _iPharmacistService;

    public AuthController(IPatientService iPatientService, ISystemAdminService iSystemAdminService, ISupplierService iSupplierService, IPharmacyAdminService iPharmacyAdminService, IDermatologistService iDermatologistService, IAuthService iAuthService, IPharmacistService iPharmacistService) {
        _iPatientService = iPatientService;
        _iSystemAdminService = iSystemAdminService;
        _iSupplierService = iSupplierService;
        _iPharmacyAdminService = iPharmacyAdminService;
        _iDermatologistService = iDermatologistService;
        _iAuthService = iAuthService;
        _iPharmacistService = iPharmacistService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/patients")
    public PatientResponse createPatient(@RequestBody PatientRequest request) throws Exception {
        return _iPatientService.createPatient(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/system-admins")
    public SystemAdminResponse createSystemAdmin(@RequestBody SystemAdminRequest request) throws Exception {
        return _iSystemAdminService.createSystemAdmin(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/pharmacy-admins")
    public PharmacyAdminResponse createPharmacyAdmin(@RequestBody PharmacyAdminRequest request) throws Exception {
        return _iPharmacyAdminService.createPharmacyAdmin(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/suppliers")
    public SupplierResponse createSupplier(@RequestBody SupplierRequest request) throws Exception {
        return _iSupplierService.createSupplier(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/dermatologists")
    public DermatologistResponse createDermatologist(@RequestBody DermatologistRequest request) throws Exception {
        return _iDermatologistService.createDermatologist(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/pharmacists")
    public PharmacistResponse createPharmacist(@RequestBody PharmacistRequest request) throws Exception {
        return _iPharmacistService.createPharmacist(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) throws Exception {
        return _iAuthService.login(request);
    }

    @PostMapping("/{id}/new-password")
    public LoginResponse firstLogin(@PathVariable Long id, @RequestBody NewPasswordRequest request) throws Exception {
        return _iAuthService.setNewPassword(id, request);
    }
}

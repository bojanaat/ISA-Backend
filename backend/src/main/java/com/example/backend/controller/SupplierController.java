package com.example.backend.controller;

import com.example.backend.dto.request.ChangePasswordRequest;
import com.example.backend.dto.request.SupplierRequest;
import com.example.backend.dto.response.SupplierResponse;
import com.example.backend.service.ISupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final ISupplierService _iSupplierService;

    public SupplierController(ISupplierService iSupplierService) {
        _iSupplierService = iSupplierService;
    }

    @GetMapping("{id}/supplier")
    public SupplierResponse getSupplier(@PathVariable Long id) {
        return _iSupplierService.getSupplier(id);
    }

    @PutMapping("{id}/supplier")
    public SupplierResponse updateSupplier(@PathVariable Long id, @RequestBody SupplierRequest request) {
        return _iSupplierService.updateSupplier(request, id);
    }

    @DeleteMapping("{id}/supplier")
    public void deleteSupplier(@PathVariable Long id) {
        _iSupplierService.deleteSupplier(id);
    }

    @GetMapping
    public Set<SupplierResponse> getAllSuppliers() throws Exception {
        return _iSupplierService.getAllSuppliers();
    }

    @PutMapping("/{id}/password/supplier")
    public void changePasswordSupplier(@PathVariable("id")Long id, @RequestBody ChangePasswordRequest request){
        _iSupplierService.changePasswordSupplier(id, request);

    }
}

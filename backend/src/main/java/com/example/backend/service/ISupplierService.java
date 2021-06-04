package com.example.backend.service;

import com.example.backend.dto.request.SupplierRequest;
import com.example.backend.dto.response.SupplierResponse;

import java.util.Set;

public interface ISupplierService {
    SupplierResponse createSupplier(SupplierRequest request) throws Exception;

    SupplierResponse getSupplier(Long id);

    SupplierResponse updateSupplier(SupplierRequest request, Long id);

    void deleteSupplier(Long id);

    Set<SupplierResponse> getAllSuppliers() throws Exception;
}

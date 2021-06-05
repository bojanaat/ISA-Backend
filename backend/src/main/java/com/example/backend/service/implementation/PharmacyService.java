package com.example.backend.service.implementation;

import com.example.backend.dto.request.PharmacyRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacyResponse;
import com.example.backend.model.Medicine;
import com.example.backend.model.Pharmacy;
import com.example.backend.repository.IPharmacyRepository;
import com.example.backend.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PharmacyService implements IPharmacyService {

    private final IPharmacyRepository _iPharmacyRepository;

    public PharmacyService(IPharmacyRepository iPharmacyRepository) {
        _iPharmacyRepository = iPharmacyRepository;
    }

    @Override
    public PharmacyResponse createPharmacy(PharmacyRequest request) throws Exception {

        Pharmacy pharmacy = new Pharmacy();
        Set<Pharmacy> pharmacies = _iPharmacyRepository.findAllByDeleted(false);
        pharmacy.setName(request.getName());
        for(Pharmacy p : pharmacies) {
            if(p.getName().equals(pharmacy.getName())) {
                throw new Exception("The pharmacy you entered already exists.");
            }
        }

        pharmacy.setName(request.getName());
        pharmacy.setAddress(request.getAddress());
        pharmacy.setDescription(request.getDescription());
        pharmacy.setDeleted(false);

        _iPharmacyRepository.save(pharmacy);

        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        pharmacyResponse.setId(pharmacy.getId());
        pharmacyResponse.setName(pharmacy.getName());
        pharmacyResponse.setAddress(pharmacy.getAddress());
        pharmacyResponse.setDescription(pharmacy.getDescription());

        return pharmacyResponse;
    }

    @Override
    public List<PharmacyResponse> getAllPharmacies() {
        List<Pharmacy> pharmacies = _iPharmacyRepository.findAll();
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies) {
            PharmacyResponse pharmacyResponse = mapToResponse(pharmacy);
            pharmacyResponses.add(pharmacyResponse);
        }
        return pharmacyResponses;
    }

    public PharmacyResponse mapToResponse(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        pharmacyResponse.setAddress(pharmacy.getAddress());
        pharmacyResponse.setId(pharmacy.getId());
        pharmacyResponse.setDescription(pharmacy.getDescription());
        pharmacyResponse.setName(pharmacy.getName());
        return pharmacyResponse;

    }


}


package com.example.backend.service.implementation;

import com.example.backend.dto.response.*;
import com.example.backend.model.Medicine;
import com.example.backend.model.Pharmacy;
import com.example.backend.model.PharmacyMeds;
import com.example.backend.repository.IMedicineRepository;
import com.example.backend.repository.IPharmacyMedsRepository;
import com.example.backend.repository.IPharmacyRepository;
import com.example.backend.service.ISearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService implements ISearchService {

    private final IMedicineRepository _iMedicineRepository;
    private final IPharmacyMedsRepository _iPharmacyMedsRepository;
    private final IPharmacyRepository _iPharmacyRepository;
    
    private final MedicineService _iMedicineService;
    private final PharmacyService _pharmacyService;

    public SearchService(IMedicineRepository iMedicineRepository, IPharmacyMedsRepository iPharmacyMedsRepository, IPharmacyRepository iPharmacyRepository, MedicineService iMedicineService, PharmacyService pharmacyService) {
        _iMedicineRepository = iMedicineRepository;
        _iPharmacyMedsRepository = iPharmacyMedsRepository;
        _iPharmacyRepository = iPharmacyRepository;
        _iMedicineService = iMedicineService;
        _pharmacyService = pharmacyService;
    }

    @Override
    public SearchMedicinesResponse searchMedicines(String name) {
        List<Medicine> medicines = filteredMedicaments(name);
        List<MedicineResponse> medicamentResponses = new ArrayList<>();
        for(Medicine med: medicines){
            medicamentResponses.add(_iMedicineService.mapToResponse(med));
        }
        return mapToSearchResponse(medicamentResponses);
    }

    @Override
    public SearchPharmacyMedsResponse searchPharmacyMedicines(String name, Long pharmacyId) {
        List<PharmacyMeds> pharmacyMeds = filteredPharmacyMedicaments(name, pharmacyId);
        List<PharmacyMedsResponse> pharmacyMedsResponses = new ArrayList<>();
        for(PharmacyMeds med: pharmacyMeds){
            pharmacyMedsResponses.add(_iMedicineService.mapPharmacyMedsToResponse(med));
        }
        return mapPharmacyMedsToSearchResponse(pharmacyMedsResponses);
    }

    @Override
    public SearchPharmaciesResponse searchPharmacies(String name, String city) {
        List<Pharmacy> pharmacies = filteredPharmacies(name, city);
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for(Pharmacy ph: pharmacies){
            pharmacyResponses.add(_pharmacyService.mapToResponse(ph));
        }
        return mapPharmaciesToResponse(pharmacyResponses);
    }

    private SearchPharmaciesResponse mapPharmaciesToResponse(List<PharmacyResponse> pharmacyResponses) {
        SearchPharmaciesResponse searchResponse = new SearchPharmaciesResponse();
        searchResponse.setPharmacyResponses(pharmacyResponses);
        return searchResponse;
    }

    private List<Pharmacy> filteredPharmacies(String name, String city) {
        List<Pharmacy> allPharmacies = _iPharmacyRepository.findAll();

        return allPharmacies
                .stream()
                .filter(pharmacy -> {
                    if(name != "") {
                        return pharmacy.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(pharmacy -> {
                    if(city != ""){
                        return pharmacy.getAddress().equals(city);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private SearchPharmacyMedsResponse mapPharmacyMedsToSearchResponse(List<PharmacyMedsResponse> pharmacyMedsResponses) {
        SearchPharmacyMedsResponse searchResponse = new SearchPharmacyMedsResponse();
        searchResponse.setPharmacyMedsResponses(pharmacyMedsResponses);
        return searchResponse;
    }

    private List<PharmacyMeds> filteredPharmacyMedicaments(String name, Long pharmacyId) {
        List<PharmacyMeds> allMeds = _iPharmacyMedsRepository.findAll();

        return allMeds
                .stream()
                .filter(pharmacyMeds -> {
                    if(name != "") {
                        return pharmacyMeds.getMedicine().getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(pharmacyMeds -> {
                    if(pharmacyId == pharmacyMeds.getPharmacy().getId()){
                        return pharmacyMeds.getPharmacy().getId().equals(pharmacyId);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());

    }

    private SearchMedicinesResponse mapToSearchResponse(List<MedicineResponse> medicamentResponses) {
        SearchMedicinesResponse searchResponse = new SearchMedicinesResponse();
        searchResponse.setMedicineResponses(medicamentResponses);
        return searchResponse;
    }

    private List<Medicine> filteredMedicaments(String name) {
        List<Medicine> allMedicaments = _iMedicineRepository.findAll();
        return allMedicaments
                .stream()
                .filter(medicament -> {
                    if(name != "") {
                        return medicament.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }
}

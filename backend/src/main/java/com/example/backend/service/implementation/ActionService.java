package com.example.backend.service.implementation;

import com.example.backend.dto.request.SubscribePatientRequest;
import com.example.backend.dto.response.PharmacyResponse;
import com.example.backend.model.Patient;
import com.example.backend.model.Pharmacy;
import com.example.backend.repository.IPatientRepository;
import com.example.backend.repository.IPharmacyRepository;
import com.example.backend.service.IActionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ActionService implements IActionService {
    private final IPharmacyRepository _iPharmacyRepository;
    private final IPatientRepository _iPatientRepository;
    private final PharmacyService _pharmacyService;

    public ActionService(IPharmacyRepository iPharmacyRepository, IPatientRepository iPatientRepository, PharmacyService pharmacyService) {
        _iPharmacyRepository = iPharmacyRepository;
        _iPatientRepository = iPatientRepository;
        _pharmacyService = pharmacyService;
    }

    @Override
    public List<PharmacyResponse> getSubscribedPharmacies(Long id) {
        Patient patient = _iPatientRepository.findOneById(id);
        List<Pharmacy> pharmacies = _iPharmacyRepository.findAll();
        List<PharmacyResponse> finalList = new ArrayList<>();
        for(Pharmacy p: pharmacies){
            if(p.getPatients().contains(patient)){
                finalList.add(_pharmacyService.mapToResponse(p));
            }
        }
        return finalList;
    }

    @Override
    public void subscribePatient(Long id, SubscribePatientRequest request) {
        Patient patient = _iPatientRepository.findOneById(id);
        Pharmacy pharmacy = _iPharmacyRepository.findOneById(request.getId());
        Collection<Pharmacy> pharmacyList = patient.getPharmacies();
        pharmacyList.add(pharmacy);
        List<Patient> patients = pharmacy.getPatients();
        patients.add(patient);
        _iPharmacyRepository.save(pharmacy);
        _iPatientRepository.save(patient);
    }

    @Override
    public void cancelSubscription(Long id, SubscribePatientRequest request) {
        Patient patient = _iPatientRepository.findOneById(id);
        Pharmacy pharmacy = _iPharmacyRepository.findOneById(request.getId());
        Collection<Pharmacy> pharmacyList = patient.getPharmacies();
        pharmacyList.remove(pharmacy);
        List<Patient> patients = pharmacy.getPatients();
        patients.remove(patient);
        _iPharmacyRepository.save(pharmacy);
        _iPatientRepository.save(patient);
    }
}

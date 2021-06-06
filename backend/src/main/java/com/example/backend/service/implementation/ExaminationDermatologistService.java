package com.example.backend.service.implementation;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.request.ReserveExaminationDermatologistRequest;
import com.example.backend.dto.response.ExaminationDermatologistResponse;
import com.example.backend.model.ExaminationDermatologist;
import com.example.backend.repository.IExaminationDermatologistRepository;
import com.example.backend.repository.IPatientRepository;
import com.example.backend.service.IExaminationDermatologistService;
import com.example.backend.utils.ExaminationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationDermatologistService implements IExaminationDermatologistService {

    private final IPatientRepository _patientRepository;
    private final IExaminationDermatologistRepository _examinationDermatologistRepository;

    private final DermatologistService _dermatologistService;
    private final PharmacyService _pharmacyService;
    private final EmailService _emailService;

    public ExaminationDermatologistService(IPatientRepository patientRepository, IExaminationDermatologistRepository examinationDermatologistRepository, DermatologistService dermatologistService, PharmacyService pharmacyService, EmailService emailService) {
        _patientRepository = patientRepository;
        _examinationDermatologistRepository = examinationDermatologistRepository;
        _dermatologistService = dermatologistService;
        _pharmacyService = pharmacyService;
        _emailService = emailService;
    }

    @Override
    public List<ExaminationDermatologistResponse> getAllAvailableExaminationsByPharmacyId(Long id) {
        List<ExaminationDermatologist> dermatologistExaminations = _examinationDermatologistRepository.findAllByPharmacy_Id(id);
        List<ExaminationDermatologistResponse> finalEx = new ArrayList<>();
        for(ExaminationDermatologist examination: dermatologistExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.AVAILABLE)){
                finalEx.add(mapExaminationToResponse(examination));
            }
        }
        return finalEx;
    }

    public ExaminationDermatologistResponse mapExaminationToResponse(ExaminationDermatologist examinationDermatologist) {
        ExaminationDermatologistResponse response = new ExaminationDermatologistResponse();
        response.setId(examinationDermatologist.getId());
        response.setDateExamination(examinationDermatologist.getDateExamination());
        response.setStartTimeExamination(examinationDermatologist.getStartTimeExamination());
        response.setEndTimeExamination(examinationDermatologist.getEndTimeExamination());
        response.setDermatologistResponse(_dermatologistService.mapToResponse(examinationDermatologist.getDermatologist()));
        response.setPharmacyResponse(_pharmacyService.mapToResponse(examinationDermatologist.getPharmacy()));
        response.setPrice(examinationDermatologist.getPrice());
        if(examinationDermatologist.getPatient() != null){
            response.setPatientId(examinationDermatologist.getPatient().getId());
        }

        response.setExaminationStatus(examinationDermatologist.getExaminationStatus().toString());
        return response;
    }

    @Override
    public boolean reserveExamination(ReserveExaminationDermatologistRequest request) {
        ExaminationDermatologist examinationDermatologist = _examinationDermatologistRepository.findOneById(request.getReservationId());
        examinationDermatologist.setPatient(_patientRepository.findOneById(request.getPatientId()));
        examinationDermatologist.setExaminationStatus(ExaminationStatus.RESERVED);
        ExaminationDermatologist savedReservation = _examinationDermatologistRepository.save(examinationDermatologist);
        _emailService.approveDermatologistExaminationReservation(savedReservation);
        return true;
    }

    @Override
    public List<ExaminationDermatologistResponse> getAllDroppedReservationByPatientId(Long id) {
        List<ExaminationDermatologistResponse> finalEx = new ArrayList<>();
        List<ExaminationDermatologist> patientExaminations = _examinationDermatologistRepository.findAllByPatient_Id(id);
        for(ExaminationDermatologist examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.DROPPED)){
                finalEx.add(mapExaminationToResponse(examination));
            }
        }
        return finalEx;
    }

    @Override
    public List<ExaminationDermatologistResponse> getAllActiveReservationByPatientId(Long id) {
        List<ExaminationDermatologistResponse> finalEx = new ArrayList<>();
        List<ExaminationDermatologist> patientExaminations = _examinationDermatologistRepository.findAllByPatient_Id(id);
        for(ExaminationDermatologist examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.AVAILABLE)){
                finalEx.add(mapExaminationToResponse(examination));
            }
        }
        return finalEx;
    }

    @Override
    public boolean cancelReservation(GetIdRequest request) {
        ExaminationDermatologist dermatologistExamination = _examinationDermatologistRepository.findOneById(request.getId());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate dateExamination = dermatologistExamination.getDateExamination();
        System.out.println(dateExamination);
        LocalDate pickupDate24Hours = dateExamination.minusDays(1);
        System.out.println(pickupDate24Hours);
        if(now.isBefore(pickupDate24Hours)){
            dermatologistExamination.setExaminationStatus(ExaminationStatus.AVAILABLE);
            _examinationDermatologistRepository.save(dermatologistExamination);
            return true;
        }
        return false;
    }

    @Override
    public List<ExaminationDermatologistResponse> getAllReservedReservationByPatientId(Long id) {
        List<ExaminationDermatologistResponse> finalEx = new ArrayList<>();
        List<ExaminationDermatologist> patientExaminations = _examinationDermatologistRepository.findAllByPatient_Id(id);
        for(ExaminationDermatologist examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.RESERVED)){
                finalEx.add(mapExaminationToResponse(examination));
            }
        }
        return finalEx;
    }
}

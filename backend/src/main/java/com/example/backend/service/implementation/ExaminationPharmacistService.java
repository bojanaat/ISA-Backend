package com.example.backend.service.implementation;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.response.ExaminationPharmacistResponse;
import com.example.backend.model.ExaminationDermatologist;
import com.example.backend.model.ExaminationPharmacist;
import com.example.backend.repository.IExaminationPharmacistRepository;
import com.example.backend.service.IExaminationPharmacistService;
import com.example.backend.utils.ExaminationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationPharmacistService implements IExaminationPharmacistService {

    private final IExaminationPharmacistRepository _examinationPharmacistRepository;

    private final ShiftPharmacistService _shiftPharmacistService;

    public ExaminationPharmacistService(IExaminationPharmacistRepository examinationPharmacistRepository, ShiftPharmacistService shiftPharmacistService) {
        _examinationPharmacistRepository = examinationPharmacistRepository;
        _shiftPharmacistService = shiftPharmacistService;
    }

    @Override
    public boolean cancelReservation(GetIdRequest request) {
        ExaminationPharmacist pharmacistExamination = _examinationPharmacistRepository.findOneById(request.getId());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate dateExamination = pharmacistExamination.getDateExamination();
        System.out.println(dateExamination);
        LocalDate pickupDate24Hours = dateExamination.minusDays(1);
        System.out.println(pickupDate24Hours);
        if(now.isBefore(pickupDate24Hours)){
            _examinationPharmacistRepository.delete(pharmacistExamination);
            return true;
        }
        return false;
    }

    @Override
    public List<ExaminationPharmacistResponse> getAllActiveReservationsByPatientId(Long id) {
        List<ExaminationPharmacistResponse> finalEx = new ArrayList<>();
        List<ExaminationPharmacist> patientExaminations = _examinationPharmacistRepository.findAllByPatient_Id(id);
        for(ExaminationPharmacist examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.AVAILABLE)){
                finalEx.add(mapToExaminationResponse(examination));
            }
        }
        return finalEx;
    }

    @Override
    public List<ExaminationPharmacistResponse> getAllDroppedReservationByPatientId(Long id) {
        List<ExaminationPharmacistResponse> finalEx = new ArrayList<>();
        List<ExaminationPharmacist> patientExaminations = _examinationPharmacistRepository.findAllByPatient_Id(id);
        for(ExaminationPharmacist examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.DROPPED)){
                finalEx.add(mapToExaminationResponse(examination));
            }
        }
        return finalEx;
    }

    public ExaminationPharmacistResponse mapToExaminationResponse(ExaminationPharmacist pharmacistExamination) {
        ExaminationPharmacistResponse response = new ExaminationPharmacistResponse();
        response.setId(pharmacistExamination.getId());
        response.setDateExamination(pharmacistExamination.getDateExamination());
        response.setStartTimeExamination(pharmacistExamination.getStartTimeExamination());
        response.setEndTimeExamination(pharmacistExamination.getEndTimeExamination());
        response.setPrice(pharmacistExamination.getPrice());
        response.setShiftPharmacistResponse(_shiftPharmacistService.mapToShiftPharmacistResponse(pharmacistExamination.getShiftPharmacist()));
        response.setPatientId(pharmacistExamination.getPatient().getId());
        response.setExaminationStatus(pharmacistExamination.getExaminationStatus().toString());
        return response;
    }


}

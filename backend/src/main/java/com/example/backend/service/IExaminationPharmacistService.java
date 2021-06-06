package com.example.backend.service;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.response.ExaminationPharmacistResponse;

import java.util.List;

public interface IExaminationPharmacistService {
    boolean cancelReservation(GetIdRequest request);

    List<ExaminationPharmacistResponse> getAllActiveReservationsByPatientId(Long id);

    List<ExaminationPharmacistResponse> getAllDroppedReservationByPatientId(Long id);
}

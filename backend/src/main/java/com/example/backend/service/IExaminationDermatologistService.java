package com.example.backend.service;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.request.ReserveExaminationDermatologistRequest;
import com.example.backend.dto.response.ExaminationDermatologistResponse;

import java.util.List;

public interface IExaminationDermatologistService {
    List<ExaminationDermatologistResponse> getAllAvailableExaminationsByPharmacyId(Long id);

    boolean reserveExamination(ReserveExaminationDermatologistRequest request);

    List<ExaminationDermatologistResponse> getAllDroppedReservationByPatientId(Long id);

    List<ExaminationDermatologistResponse> getAllActiveReservationByPatientId(Long id);

    boolean cancelReservation(GetIdRequest request);

    List<ExaminationDermatologistResponse> getAllReservedReservationByPatientId(Long id);
}

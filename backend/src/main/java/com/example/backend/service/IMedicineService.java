package com.example.backend.service;

import com.example.backend.dto.request.AllergyRequest;
import com.example.backend.dto.request.CancelMedReservationRequest;
import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.request.ReserveMedicamentRequest;
import com.example.backend.dto.response.MedReservationResponse;
import com.example.backend.dto.response.MedicineOrderResponse;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacyMedsResponse;

import java.util.List;

public interface IMedicineService {
    MedicineResponse createMed(CreateMedRequest request);

    List<MedicineResponse> getAllMedicaments();

    void addNewAlergy(Long id, AllergyRequest request);

    List<MedicineResponse> getMedicamentPatientIsNotAllergic(Long id);

    List<PharmacyMedsResponse> getPharmacyMedicines(Long id);

    PharmacyMedsResponse reserveMedicament(ReserveMedicamentRequest request, Long id);

    List<MedReservationResponse> getReservedActiveMedicines(Long id);

    boolean cancelMedReservation(CancelMedReservationRequest request);

    List<MedReservationResponse> getReservedDroppedMedicines(Long id);

    List<MedicineOrderResponse> getAllMedicinesByOrderId(Long id);
}

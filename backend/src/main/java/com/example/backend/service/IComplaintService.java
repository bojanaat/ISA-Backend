package com.example.backend.service;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.*;

import java.util.List;

public interface IComplaintService {
    ComplaintDermatologistResponse createComplaintForDermatologist(CreateComplaintForDermatologistRequest request);

    ComplaintPharmacistResponse createComplaintForPharmacist(CreateComplaintForPharmacistRequest request);

    ComplaintPharmacyResponse createComplaintForPharmacy(CreateComplaintForPharmacyRequest request);

    ComplaintMedicineResponse createComplaintForMedicine(CreateComplaintForMedicineRequest request);

    List<ComplaintDermatologistResponse> getAllComplaintsForDermatologist();

    List<ComplaintPharmacistResponse> getAllComplaintsForPharmacist();

    List<ComplaintPharmacyResponse> getAllComplaintsForPharmacy();

    List<ComplaintMedicineResponse> getAllComplaintsForMedicine();

    boolean answerOnComplaint(AnswerOnComplaintRequest request);
}

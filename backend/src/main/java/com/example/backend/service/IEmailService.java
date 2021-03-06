package com.example.backend.service;

import com.example.backend.model.MedicineReservation;
import com.example.backend.model.Patient;

public interface IEmailService {

    void approveRegistrationRequest(Patient patient);

    void denyRegistrationRequest(Patient patient, String reason);

    void approveMedicineReservation(MedicineReservation savedReservation);
}

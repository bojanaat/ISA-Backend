package com.example.backend.service;

import com.example.backend.dto.request.ApprovePatientRequest;
import com.example.backend.dto.request.ChangePasswordRequest;
import com.example.backend.dto.request.DenyPatientRequest;
import com.example.backend.dto.request.PatientRequest;
import com.example.backend.dto.response.PatientResponse;

import java.util.Set;

public interface IPatientService {
    PatientResponse createPatient(PatientRequest request) throws Exception;

    PatientResponse getPatient(Long id);

    PatientResponse updatePatient(PatientRequest request, Long id);

    void deletePatient(Long id);

    Set<PatientResponse> getAllPatients() throws Exception;

    Set<PatientResponse> getAllPendingRequests() throws Exception;

    PatientResponse approveRegistrationRequest(ApprovePatientRequest request);

    void denyRegistrationRequest(Long patientId, DenyPatientRequest request);

    PatientResponse activateRegistration(ApprovePatientRequest request) throws Exception;

    void changePasswordPatient(Long id, ChangePasswordRequest request);
}

package com.example.backend.service;

import com.example.backend.dto.request.SubscribePatientRequest;
import com.example.backend.dto.response.PharmacyResponse;

import java.util.List;

public interface IActionService {
    List<PharmacyResponse> getSubscribedPharmacies(Long id);

    void subscribePatient(Long id, SubscribePatientRequest request);

    void cancelSubscription(Long id, SubscribePatientRequest request);
}

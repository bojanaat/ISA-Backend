package com.example.backend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchPharmacyMedsResponse {
    private List<PharmacyMedsResponse> pharmacyMedsResponses;
}

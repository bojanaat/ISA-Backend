package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedReservationResponse {
    private Long id;
    private LocalDate pickDate;
    private PharmacyMedsResponse pharmacyMedsResponse;
    private Long patientId;
    private String medicamentReservationStatus;
}

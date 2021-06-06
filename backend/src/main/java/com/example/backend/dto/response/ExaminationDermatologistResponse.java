package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ExaminationDermatologistResponse {

    private Long id;
    private LocalTime startTimeExamination;
    private LocalTime endTimeExamination;
    private LocalDate dateExamination;
    private DermatologistResponse dermatologistResponse;
    private double price;
    private PharmacyResponse pharmacyResponse;
    private String examinationStatus;
    private Long patientId;
}

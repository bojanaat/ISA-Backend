package com.example.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ExaminationPharmacistResponse {
    private Long id;
    private LocalTime startTimeExamination;
    private LocalTime endTimeExamination;
    private LocalDate dateExamination;
    private double price;
    private ShiftPharmacistResponse shiftPharmacistResponse;
    private String examinationStatus;
    private Long patientId;
}

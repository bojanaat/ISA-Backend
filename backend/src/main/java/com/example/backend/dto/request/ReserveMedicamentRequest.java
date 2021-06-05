package com.example.backend.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReserveMedicamentRequest {
    private Long pharmacyMedId;
    private Long patientId;
    private String pickDate;
}

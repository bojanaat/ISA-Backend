package com.example.backend.dto.request;

import lombok.Data;

@Data
public class ReserveExaminationDermatologistRequest {
    private Long reservationId;
    private Long patientId;
}

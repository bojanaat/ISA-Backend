package com.example.backend.dto.request;

import lombok.Data;

@Data
public class AnswerOnComplaintRequest {
    private Long complaintId;
    private Long patientId;
    private String text;
}

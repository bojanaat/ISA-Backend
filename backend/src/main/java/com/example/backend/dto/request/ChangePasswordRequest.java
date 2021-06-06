package com.example.backend.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String pass;
    private String newPass;
    private String rePass;
}

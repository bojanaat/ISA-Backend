package com.example.backend.dto.request;

import lombok.Data;

@Data
public class NewPasswordRequest {

    private String password;
    private String rePassword;
}

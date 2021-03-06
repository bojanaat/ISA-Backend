package com.example.backend.dto.request;

import lombok.Data;

@Data
public class PatientRequest {

    private String email; //not possible to update
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
}

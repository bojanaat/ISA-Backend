package com.example.backend.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DermatologistRequest {

    private String email; //not possible to update
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private Long pharmacyId;
}

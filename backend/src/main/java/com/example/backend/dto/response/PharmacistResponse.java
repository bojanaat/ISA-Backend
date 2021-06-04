package com.example.backend.dto.response;

import lombok.Data;

@Data
public class PharmacistResponse {

    private Long id;
    private String email; //not possible to update
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
}

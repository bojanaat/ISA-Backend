package com.example.backend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class PharmacyResponse {

    private Long id;
    private String name;
    private String address;
    private String description;
}

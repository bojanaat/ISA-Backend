package com.example.backend.dto.response;

import com.example.backend.utils.UserType;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String email; //not possible to update
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private UserType userType;
    private boolean setNewPassword;
}

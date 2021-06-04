package com.example.backend.service;

import com.example.backend.dto.request.UserRequest;
import com.example.backend.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserRequest userRequest) throws Exception;
}

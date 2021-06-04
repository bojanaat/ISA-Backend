package com.example.backend.service;

import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.NewPasswordRequest;
import com.example.backend.dto.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest request) throws Exception;

    LoginResponse setNewPassword(Long id, NewPasswordRequest request) throws Exception;
}

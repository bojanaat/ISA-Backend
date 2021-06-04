package com.example.backend.service;

import com.example.backend.dto.request.SystemAdminRequest;
import com.example.backend.dto.response.SystemAdminResponse;

import java.util.Set;

public interface ISystemAdminService {
    SystemAdminResponse createSystemAdmin(SystemAdminRequest request) throws Exception;

    SystemAdminResponse getSystemAdmin(Long id);

    SystemAdminResponse updateSystemAdmin(SystemAdminRequest request, Long id);

    void deleteSystemAdmin(Long id);

    Set<SystemAdminResponse> getAllSystemAdmins() throws Exception;
}

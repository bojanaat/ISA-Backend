package com.example.backend.controller;

import com.example.backend.dto.request.SystemAdminRequest;
import com.example.backend.dto.response.SystemAdminResponse;
import com.example.backend.service.ISystemAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/system-admins")
public class SystemAdminController {
    private final ISystemAdminService _iSystemAdminService;

    public SystemAdminController(ISystemAdminService iSystemAdminService) {
        _iSystemAdminService = iSystemAdminService;
    }

    @GetMapping("/{id}/system-admin")
    public SystemAdminResponse getSystemAdmin(@PathVariable Long id) {
        return _iSystemAdminService.getSystemAdmin(id);
    }

    @PutMapping("{id}/system-admin")
    public SystemAdminResponse updateSystemAdmin(@PathVariable Long id, @RequestBody SystemAdminRequest request) {
        return _iSystemAdminService.updateSystemAdmin(request, id);
    }

    @DeleteMapping("/{id}/system-admin")
    public void deleteSystemAdmin(@PathVariable Long id) {
        _iSystemAdminService.deleteSystemAdmin(id);
    }

    @GetMapping
    public Set<SystemAdminResponse> getAllSystemAdmins() throws Exception {
        return _iSystemAdminService.getAllSystemAdmins();
    }

}

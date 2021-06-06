package com.example.backend.controller;

import com.example.backend.dto.response.PharmacistResponse;
import com.example.backend.service.IPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    private final IPharmacistService _pharmacistService;

    public PharmacistController(IPharmacistService pharmacistService) {
        _pharmacistService = pharmacistService;
    }


    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getPharmacists(@PathVariable("id") Long id){
        List<PharmacistResponse> pharmacistResponses = _pharmacistService.getPharmacists(id);
        if(pharmacistResponses != null) {
            return new ResponseEntity<>(pharmacistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}

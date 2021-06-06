package com.example.backend.controller;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.response.ExaminationPharmacistResponse;
import com.example.backend.service.IExaminationPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examination-pharmacist")
public class ExaminationPharmacistController {

    private final IExaminationPharmacistService _examinationPharmacistService;

    public ExaminationPharmacistController(IExaminationPharmacistService examinationPharmacistService) {
        _examinationPharmacistService = examinationPharmacistService;
    }

    @GetMapping("/dropped/{id}/patient")
    public ResponseEntity<?> getAllDroppedReservationByPatientId(@PathVariable("id") Long id){
        List<ExaminationPharmacistResponse> responses = _examinationPharmacistService.getAllDroppedReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations for this patient doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/patient")
    public ResponseEntity<?> getAllActiveReservationsByPatientId(@PathVariable("id") Long id){
        List<ExaminationPharmacistResponse> responses = _examinationPharmacistService.getAllActiveReservationsByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations for this patient doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel")
    public boolean cancelReservation(@RequestBody GetIdRequest request){
        return  _examinationPharmacistService.cancelReservation(request);
    }
}

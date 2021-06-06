package com.example.backend.controller;

import com.example.backend.dto.request.GetIdRequest;
import com.example.backend.dto.request.ReserveExaminationDermatologistRequest;
import com.example.backend.dto.response.ExaminationDermatologistResponse;
import com.example.backend.service.IExaminationDermatologistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examination-dermatologist")
public class ExaminationDermatologistController {

    private final IExaminationDermatologistService _iExaminationDermatologistService;

    public ExaminationDermatologistController(IExaminationDermatologistService iExaminationDermatologistService) {
        _iExaminationDermatologistService = iExaminationDermatologistService;
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllAvailableExaminationsByPharmacyId(@PathVariable("id") Long id){
        List<ExaminationDermatologistResponse> responses = _iExaminationDermatologistService.getAllAvailableExaminationsByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/reserve")
    public boolean reserveExamination(@RequestBody ReserveExaminationDermatologistRequest request){
        return  _iExaminationDermatologistService.reserveExamination(request);
    }

    @GetMapping("/dropped/{id}/patient")
    public ResponseEntity<?> getAllDroppedReservationByPatientId(@PathVariable("id") Long id){
        List<ExaminationDermatologistResponse> responses = _iExaminationDermatologistService.getAllDroppedReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/patient")
    public ResponseEntity<?> getAllActiveReservationByPatientId(@PathVariable("id") Long id){
        List<ExaminationDermatologistResponse> responses = _iExaminationDermatologistService.getAllActiveReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reserved/{id}/patient")
    public ResponseEntity<?> getAllReservedReservationByPatientId(@PathVariable("id") Long id){
        List<ExaminationDermatologistResponse> responses = _iExaminationDermatologistService.getAllReservedReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel")
    public boolean cancelReservation(@RequestBody GetIdRequest request){
        return  _iExaminationDermatologistService.cancelReservation(request);
    }

}

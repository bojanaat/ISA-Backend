package com.example.backend.controller;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.*;
import com.example.backend.service.IComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final IComplaintService _iComplaintService;

    public ComplaintController(IComplaintService iComplaintService) {
        _iComplaintService = iComplaintService;
    }

    @PostMapping("/dermatologist")
    public ResponseEntity<?> createComplaintForDermatologist(@RequestBody CreateComplaintForDermatologistRequest request){
        ComplaintDermatologistResponse response = _iComplaintService.createComplaintForDermatologist(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pharmacist")
    public ResponseEntity<?> createComplaintForPharmacist(@RequestBody CreateComplaintForPharmacistRequest request){
        ComplaintPharmacistResponse response = _iComplaintService.createComplaintForPharmacist(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pharmacy")
    public ResponseEntity<?> createComplaintForPharmacy(@RequestBody CreateComplaintForPharmacyRequest request){
        ComplaintPharmacyResponse response = _iComplaintService.createComplaintForPharmacy(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/medicine")
    public ResponseEntity<?> createComplaintForMedicine(@RequestBody CreateComplaintForMedicineRequest request){
        ComplaintMedicineResponse response = _iComplaintService.createComplaintForMedicine(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dermatologist")
    public List<ComplaintDermatologistResponse> getAllComplaintsForDermatologist(){
        return _iComplaintService.getAllComplaintsForDermatologist();
    }

    @GetMapping("/pharmacist")
    public List<ComplaintPharmacistResponse> getAllComplaintsForPharmacist(){
        return _iComplaintService.getAllComplaintsForPharmacist();
    }

    @GetMapping("/pharmacy")
    public List<ComplaintPharmacyResponse> getAllComplaintsForPharmacy(){
        return _iComplaintService.getAllComplaintsForPharmacy();
    }

    @GetMapping("/medicine")
    public List<ComplaintMedicineResponse> getAllComplaintsForMedicine(){
        return _iComplaintService.getAllComplaintsForMedicine();
    }

    @PutMapping("/answer")
    public boolean answerOnComplaint(@RequestBody AnswerOnComplaintRequest request){
        return  _iComplaintService.answerOnComplaint(request);
    }
}

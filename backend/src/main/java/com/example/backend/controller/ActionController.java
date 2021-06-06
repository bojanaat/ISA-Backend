package com.example.backend.controller;

import com.example.backend.dto.request.SubscribePatientRequest;
import com.example.backend.dto.response.ActionResponse;
import com.example.backend.dto.response.PharmacyResponse;
import com.example.backend.service.IActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    private final IActionService _iActionService;

    public ActionController(IActionService iActionService) {
        _iActionService = iActionService;
    }


    @GetMapping("/{id}/subscribed-patient")
    public ResponseEntity<?> getSubscribedPharmacies(@PathVariable("id") Long id){
        List<PharmacyResponse> pharmacyResponses = _iActionService.getSubscribedPharmacies(id);
        if(pharmacyResponses != null) {
            return new ResponseEntity<>(pharmacyResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacies doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/subscribe/{id}/patient")
    public void subscribePatient(@PathVariable("id")Long id, @RequestBody SubscribePatientRequest request){
        _iActionService.subscribePatient(id, request);
    }

    @PutMapping("/cancel/{id}/patient")
    public void cancelSubscription(@PathVariable("id")Long id, @RequestBody SubscribePatientRequest request){
        _iActionService.cancelSubscription(id, request);
    }



}

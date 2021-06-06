package com.example.backend.controller;

import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.request.CreateOfferRequest;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.OfferResponse;
import com.example.backend.dto.response.OrderResponse;
import com.example.backend.service.IOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final IOfferService _iOfferService;

    public OfferController(IOfferService iOfferService) {
        _iOfferService = iOfferService;
    }

    @PostMapping()
    public ResponseEntity<?> createOffer(@RequestBody CreateOfferRequest request){
        OfferResponse response = _iOfferService.createOffer(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public List<OfferResponse> getAllOffersBySupplierId(@PathVariable Long id){
        return _iOfferService.getAllOffersBySupplierId(id);
    }
}

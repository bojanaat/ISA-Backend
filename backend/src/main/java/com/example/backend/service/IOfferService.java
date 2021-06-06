package com.example.backend.service;

import com.example.backend.dto.request.CreateOfferRequest;
import com.example.backend.dto.response.OfferResponse;

public interface IOfferService {
    OfferResponse createOffer(CreateOfferRequest request);
}

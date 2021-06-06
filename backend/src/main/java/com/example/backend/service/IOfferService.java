package com.example.backend.service;

import com.example.backend.dto.request.CreateOfferRequest;
import com.example.backend.dto.response.OfferResponse;
import com.example.backend.dto.response.OrderResponse;

import java.util.List;

public interface IOfferService {
    OfferResponse createOffer(CreateOfferRequest request);

    List<OfferResponse> getAllOffersBySupplierId(Long id);
}

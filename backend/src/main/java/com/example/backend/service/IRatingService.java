package com.example.backend.service;

import com.example.backend.dto.request.RatingRequest;
import com.example.backend.dto.response.RatingResponse;

public interface IRatingService {
    RatingResponse rateDermatologist(RatingRequest rateRequest);

    RatingResponse ratePharmacist(RatingRequest rateRequest);

    RatingResponse rateMedicament(RatingRequest rateRequest);

    RatingResponse ratePharmacy(RatingRequest rateRequest);
}

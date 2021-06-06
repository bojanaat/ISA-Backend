package com.example.backend.service.implementation;

import com.example.backend.dto.request.RatingRequest;
import com.example.backend.dto.response.RatingResponse;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import com.example.backend.service.IRatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

    private final IRatingRepository _ratingRepository;
    private final IPharmacyRepository _pharmacyRepository;
    private final IMedicineRepository _medicamentRepository;
    private final IPharmacistRepository _pharmacistRepository;
    private final IDermatologistRepository _dermatologistRepository;

    public RatingService(IRatingRepository ratingRepository, IPharmacyRepository pharmacyRepository, IMedicineRepository medicamentRepository, IPharmacistRepository pharmacistRepository, IDermatologistRepository dermatologistRepository) {
        _ratingRepository = ratingRepository;
        _pharmacyRepository = pharmacyRepository;
        _medicamentRepository = medicamentRepository;
        _pharmacistRepository = pharmacistRepository;
        _dermatologistRepository = dermatologistRepository;
    }

    @Override
    public RatingResponse ratePharmacy(RatingRequest rateRequest) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setPharmacy(pharmacy);
        Rating savedRating = _ratingRepository.save(rating);
        pharmacy.getRatings().add(savedRating);
        _pharmacyRepository.save(pharmacy);
        return mapRatingToRatingResponse(savedRating);
    }

    private RatingResponse mapRatingToRatingResponse(Rating savedRating) {
        RatingResponse response = new RatingResponse();
        response.setId(savedRating.getId());
        return response;
    }

    @Override
    public RatingResponse rateMedicament(RatingRequest rateRequest) {
        Medicine medicine = _medicamentRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setMedicine(medicine);
        Rating savedRating = _ratingRepository.save(rating);
        medicine.getRatings().add(savedRating);
        _medicamentRepository.save(medicine);
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public RatingResponse ratePharmacist(RatingRequest rateRequest) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setPharmacist(pharmacist);
        Rating savedRating = _ratingRepository.save(rating);
        pharmacist.getRatings().add(savedRating);
        _pharmacistRepository.save(pharmacist);
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public RatingResponse rateDermatologist(RatingRequest rateRequest) {
        Dermatologist dermatologist = _dermatologistRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setDermatologist(dermatologist);
        Rating savedRating = _ratingRepository.save(rating);
        dermatologist.getRatings().add(savedRating);
        _dermatologistRepository.save(dermatologist);
        return mapRatingToRatingResponse(savedRating);
    }
}


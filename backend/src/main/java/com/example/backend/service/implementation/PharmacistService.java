package com.example.backend.service.implementation;

import com.example.backend.dto.request.PharmacistRequest;
import com.example.backend.dto.request.UserRequest;
import com.example.backend.dto.response.DermatologistResponse;
import com.example.backend.dto.response.PharmacistResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.Dermatologist;
import com.example.backend.model.Pharmacist;
import com.example.backend.model.Pharmacy;
import com.example.backend.model.User;
import com.example.backend.repository.IPharmacistRepository;
import com.example.backend.repository.IPharmacyRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.service.IPharmacistService;
import com.example.backend.service.IUserService;
import com.example.backend.utils.UserType;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService implements IPharmacistService {

    private final IPharmacistRepository _iPharmacistRepository;
    private final IUserRepository _iUserRepository;
    private final IPharmacyRepository _iPharmacyRepository;

    private final IUserService _iUserService;

    public PharmacistService(IPharmacistRepository iPharmacistRepository, IUserRepository iUserRepository, IPharmacyRepository iPharmacyRepository, IUserService iUserService) {
        _iPharmacistRepository = iPharmacistRepository;
        _iUserRepository = iUserRepository;
        _iPharmacyRepository = iPharmacyRepository;
        _iUserService = iUserService;
    }


    @Override
    public PharmacistResponse createPharmacist(PharmacistRequest request) throws Exception {
        Pharmacy pharmacy = _iPharmacyRepository.findOneById(request.getPharmacyId());
        if(pharmacy == null){
            throw new Exception("You haven't assigned a pharmacy to the new created dermatologist.");
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(request.getEmail());
        userRequest.setPassword(request.getPassword());
        userRequest.setRePassword(request.getRePassword());
        userRequest.setFirstName(request.getFirstName());
        userRequest.setLastName(request.getLastName());
        userRequest.setAddress(request.getAddress());
        userRequest.setCity(request.getCity());
        userRequest.setCountry(request.getCountry());
        userRequest.setPhoneNumber(request.getPhoneNumber());
        userRequest.setUserType(UserType.PHARMACIST);

        UserResponse userResponse = _iUserService.createUser(userRequest);

        User user = _iUserRepository.findOneById(userResponse.getId());
        user.setId(userResponse.getId());

        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setUser(user);
        pharmacist.setPharmacy(pharmacy);

        Pharmacist savedPharmacist = _iPharmacistRepository.save(pharmacist);

        return mapToResponse(savedPharmacist);
    }

    private PharmacistResponse mapToResponse(Pharmacist savedPharmacist) {

        PharmacistResponse pharmacistResponse = new PharmacistResponse();
        User user = savedPharmacist.getUser();
        pharmacistResponse.setEmail(user.getEmail());
        pharmacistResponse.setId(savedPharmacist.getId());
        pharmacistResponse.setAddress(user.getAddress());
        pharmacistResponse.setCity(user.getCity());
        pharmacistResponse.setCountry(user.getCountry());
        pharmacistResponse.setFirstName(user.getFirstName());
        pharmacistResponse.setLastName(user.getLastName());
        pharmacistResponse.setPhoneNumber(user.getPhoneNumber());
        return pharmacistResponse;
    }
}

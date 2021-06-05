package com.example.backend.service.implementation;

import com.example.backend.dto.request.DermatologistRequest;
import com.example.backend.dto.request.UserRequest;
import com.example.backend.dto.response.DermatologistResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.Dermatologist;
import com.example.backend.model.Pharmacy;
import com.example.backend.model.User;
import com.example.backend.repository.IDermatologistRepository;
import com.example.backend.repository.IPharmacyRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.service.IDermatologistService;
import com.example.backend.service.IUserService;
import com.example.backend.utils.UserType;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService implements IDermatologistService {

    private final IDermatologistRepository _iDermatologistRepository;
    private final IUserRepository _iUserRepository;
    private final IPharmacyRepository _iPharmacyRepository;

    private final IUserService _iUserService;

    public DermatologistService(IDermatologistRepository iDermatologistRepository, IUserRepository iUserRepository, IPharmacyRepository iPharmacyRepository, IUserService iUserService) {
        _iDermatologistRepository = iDermatologistRepository;
        _iUserRepository = iUserRepository;
        _iPharmacyRepository = iPharmacyRepository;
        _iUserService = iUserService;
    }

    @Override
    public DermatologistResponse createDermatologist(DermatologistRequest request) throws Exception {
      /*  Pharmacy pharmacy = _iPharmacyRepository.findOneById(request.getPharmacyId());
        if(pharmacy == null){
            throw new Exception("You haven't assigned a pharmacy to the new created dermatologist.");
        }*/

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
        userRequest.setUserType(UserType.DERMATOLOGIST);

        UserResponse userResponse = _iUserService.createUser(userRequest);

        User user = _iUserRepository.findOneById(userResponse.getId());
        user.setId(userResponse.getId());

        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setUser(user);
        //dermatologist.setPharmacy(pharmacy);

        Dermatologist savedDermatologist = _iDermatologistRepository.save(dermatologist);

        return mapToResponse(savedDermatologist);
    }

    private DermatologistResponse mapToResponse(Dermatologist savedDermatologist) {

        DermatologistResponse dermatologistResponse = new DermatologistResponse();
        User user = savedDermatologist.getUser();
        dermatologistResponse.setEmail(user.getEmail());
        dermatologistResponse.setId(savedDermatologist.getId());
        dermatologistResponse.setAddress(user.getAddress());
        dermatologistResponse.setCity(user.getCity());
        dermatologistResponse.setCountry(user.getCountry());
        dermatologistResponse.setFirstName(user.getFirstName());
        dermatologistResponse.setLastName(user.getLastName());
        dermatologistResponse.setPhoneNumber(user.getPhoneNumber());
        return dermatologistResponse;
    }

}

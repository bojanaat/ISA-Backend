package com.example.backend.service.implementation;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.PatientResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.Patient;
import com.example.backend.model.User;
import com.example.backend.repository.IPatientRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.service.IEmailService;
import com.example.backend.service.IPatientService;
import com.example.backend.service.IUserService;
import com.example.backend.utils.RequestType;
import com.example.backend.utils.UserType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository _iPatientRepository;
    private final IUserRepository _iUserRepository;
    private final IUserService _iUserService;
    private final IEmailService _iEmailService;
    private final PasswordEncoder _passwordEncoder;

    public PatientService(IPatientRepository iPatientRepository, IUserRepository iUserRepository, IUserService iUserService, IEmailService iEmailService, PasswordEncoder passwordEncoder) {
        _iPatientRepository = iPatientRepository;
        _iUserRepository = iUserRepository;
        _iUserService = iUserService;
        _iEmailService = iEmailService;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public PatientResponse createPatient(PatientRequest request) throws Exception {

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
        userRequest.setUserType(UserType.PATIENT);
        System.out.println("odo da kreiram usera");
        System.out.println(userRequest.getEmail());



        UserResponse userResponse = _iUserService.createUser(userRequest);
        System.out.println("Kreirao sam usera");


        User user = _iUserRepository.findOneById(userResponse.getId());
        user.setId(userResponse.getId());

        System.out.println(user.getEmail());


        Patient patient = new Patient();
        patient.setUser(user);
        System.out.println("Setovao sam pacijentu usera");

        patient.setRequestType(RequestType.PENDING);

        Patient savedPatient = _iPatientRepository.save(patient);

        System.out.println("Sacuvao sam pacijenta i idem u maper");


        return mapToResponse(savedPatient);
    }

    @Override
    public PatientResponse getPatient(Long id) {
        Patient patient = _iPatientRepository.findOneById(id);

        return mapToResponse(patient);
    }

    @Override
    public PatientResponse updatePatient(PatientRequest request, Long id) {
        Patient patient = _iPatientRepository.findOneById(id);

        patient.getUser().setFirstName(request.getFirstName());
        patient.getUser().setLastName(request.getLastName());
        patient.getUser().setAddress(request.getAddress());
        patient.getUser().setCity(request.getCity());
        patient.getUser().setCountry(request.getCountry());
        patient.getUser().setPhoneNumber(request.getPhoneNumber());

        Patient savedPatient = _iPatientRepository.save(patient);

        return mapToResponse(savedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = _iPatientRepository.findOneById(id);
        patient.getUser().setDeleted(true);

        _iPatientRepository.save(patient);
    }

    @Override
    public Set<PatientResponse> getAllPatients() throws Exception {
        Set<Patient> patients = _iPatientRepository.findAllByRequestTypeAndUser_Deleted(RequestType.APPROVED, false);

        if(patients.isEmpty()){
            throw new Exception("There aren't any patients in the system.");
        }

        return patients.stream().map(patient -> mapToResponse(patient))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PatientResponse> getAllPendingRequests() throws Exception {
        Set<Patient> patients = _iPatientRepository.findAllByRequestType(RequestType.PENDING);

        if(patients.isEmpty()){
            throw new Exception("There aren't any pending registration requests.");
        }

        return patients.stream().map(patient -> mapToResponse(patient))
                .collect(Collectors.toSet());
    }

    @Override
    public PatientResponse approveRegistrationRequest(ApprovePatientRequest request) {
        System.out.println(request.getPatientId());
        System.out.println("ovome hocu dapromenim status");

        Patient patient = _iPatientRepository.findOneById(request.getPatientId());
        System.out.println(patient.getId());
        System.out.println("ovog sam nasla u repozitoriju");
        patient.setRequestType(RequestType.WAITING_FOR_ACTIVATION);
        Patient savedPatient = _iPatientRepository.save(patient);

        _iEmailService.approveRegistrationRequest(savedPatient);

        return mapToResponse(savedPatient);
    }

    @Override
    public void denyRegistrationRequest(Long patientId, DenyPatientRequest request) {
        Patient patient = _iPatientRepository.findOneById(patientId);
        patient.setRequestType(RequestType.DENIED);
        Patient savedPatient = _iPatientRepository.save(patient);

        _iEmailService.denyRegistrationRequest(savedPatient, request.getMessage());
    }

    @Override
    public PatientResponse activateRegistration(ApprovePatientRequest request) throws Exception {
        Patient patient = _iPatientRepository.findOneById(request.getPatientId());

        if(patient.getRequestType().equals(RequestType.APPROVED)){
            throw new Exception("Your account has already been activated.");
        } else if(patient.getRequestType().equals(RequestType.WAITING_FOR_ACTIVATION)){
            patient.setRequestType(RequestType.APPROVED);
            Patient savedPatient = _iPatientRepository.save(patient);
            return mapToResponse(savedPatient);
        }
        throw new Exception("Your account has been deleted.");
    }

    @Override
    public void changePasswordPatient(Long id, ChangePasswordRequest request) {
        Patient patient = _iPatientRepository.findOneById(id);
        User user = patient.getUser();
        if(_passwordEncoder.matches(request.getPass(), user.getPassword())){
            if(request.getRePass().equals(request.getNewPass())){
                user.setPassword(_passwordEncoder.encode(request.getNewPass()));
                Patient savedPatient = _iPatientRepository.save(patient);
                user.setPatient(savedPatient);
                _iUserRepository.save(user);

            }
        }
    }

    public PatientResponse mapToResponse(Patient savedPatient) {
        System.out.println("Usao sam u maper");

        PatientResponse patientResponse = new PatientResponse();
        User user = savedPatient.getUser();
        patientResponse.setEmail(user.getEmail());
        patientResponse.setId(savedPatient.getId());
        patientResponse.setAddress(user.getAddress());
        patientResponse.setCity(user.getCity());
        patientResponse.setCountry(user.getCountry());
        patientResponse.setFirstName(user.getFirstName());
        patientResponse.setLastName(user.getLastName());
        patientResponse.setPhoneNumber(user.getPhoneNumber());
        System.out.println(patientResponse.getEmail());

        return patientResponse;
    }
}

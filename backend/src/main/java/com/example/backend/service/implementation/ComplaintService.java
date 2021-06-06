package com.example.backend.service.implementation;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.*;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import com.example.backend.service.IComplaintService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {

    private final IComplaintRepository _iComplaintRepository;
    private final IDermatologistRepository _iDermatologistRepository;
    private final IPharmacistRepository _iPharmacistRepository;
    private final IPharmacyRepository _iPharmacyRepository;
    private final IMedicineRepository _iMedicineRepository;
    private final IPatientRepository _iPatientRepository;

    private final PatientService _iPatientService;
    private final PharmacistService _iPharmacistService;
    private final DermatologistService _iDermatologistService;
    private final MedicineService _iMedicineService;
    private final PharmacyService _iPharmacyService;
    private final EmailService _iEmailService;

    public ComplaintService(IComplaintRepository iComplaintRepository, IDermatologistRepository iDermatologistRepository, IPharmacistRepository iPharmacistRepository, IPharmacyRepository iPharmacyRepository, IMedicineRepository iMedicineRepository, IPatientRepository iPatientRepository, PatientService iPatientService, PharmacistService iPharmacistService, DermatologistService iDermatologistService, MedicineService iMedicineService, PharmacyService iPharmacyService, EmailService iEmailService) {
        _iComplaintRepository = iComplaintRepository;
        _iDermatologistRepository = iDermatologistRepository;
        _iPharmacistRepository = iPharmacistRepository;
        _iPharmacyRepository = iPharmacyRepository;
        _iMedicineRepository = iMedicineRepository;
        _iPatientRepository = iPatientRepository;
        _iPatientService = iPatientService;
        _iPharmacistService = iPharmacistService;
        _iDermatologistService = iDermatologistService;
        _iMedicineService = iMedicineService;
        _iPharmacyService = iPharmacyService;
        _iEmailService = iEmailService;
    }

    @Override
    public ComplaintDermatologistResponse createComplaintForDermatologist(CreateComplaintForDermatologistRequest request) {
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacy(_iPharmacyRepository.findOneById(request.getPharmacyId()));
        complaint.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        Dermatologist dermatologist = _iDermatologistRepository.findOneById(request.getDermatologistId());
        complaint.setDermatologist(_iDermatologistRepository.findOneById(request.getDermatologistId()));
        complaint.setText(request.getText());
        Complaint savedComplaint = _iComplaintRepository.save(complaint);
        dermatologist.getComplaints().add(savedComplaint);
        _iDermatologistRepository.save(dermatologist);
        return mapDermatologistToResponse(savedComplaint);
    }

    private ComplaintDermatologistResponse mapDermatologistToResponse(Complaint savedComplaint) {
        ComplaintDermatologistResponse dermatologistResponse = new ComplaintDermatologistResponse();
        dermatologistResponse.setText(savedComplaint.getText());
        dermatologistResponse.setId(savedComplaint.getId());
        dermatologistResponse.setAnswered(savedComplaint.isAnswered());
        dermatologistResponse.setPatientResponse(_iPatientService.mapToResponse(savedComplaint.getPatient()));
        dermatologistResponse.setDermatologistResponse(_iDermatologistService.mapToResponse(savedComplaint.getDermatologist()));

        return dermatologistResponse;
    }


    @Override
    public ComplaintPharmacistResponse createComplaintForPharmacist(CreateComplaintForPharmacistRequest request) {
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacy(_iPharmacyRepository.findOneById(request.getPharmacyId()));
        complaint.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        Dermatologist dermatologist = _iDermatologistRepository.findOneById(request.getPharmacistId());
        complaint.setPharmacist(_iPharmacistRepository.findOneById(request.getPharmacistId()));
        complaint.setText(request.getText());
        Complaint savedComplaint = _iComplaintRepository.save(complaint);
        dermatologist.getComplaints().add(savedComplaint);
        _iDermatologistRepository.save(dermatologist);
        return mapPharmacistToResponse(savedComplaint);
    }

    private ComplaintPharmacistResponse mapPharmacistToResponse(Complaint savedComplaint) {
        ComplaintPharmacistResponse pharmacistResponse = new ComplaintPharmacistResponse();
        pharmacistResponse.setText(savedComplaint.getText());
        pharmacistResponse.setId(savedComplaint.getId());
        pharmacistResponse.setAnswered(savedComplaint.isAnswered());
        pharmacistResponse.setPatientResponse(_iPatientService.mapToResponse(savedComplaint.getPatient()));
        pharmacistResponse.setPharmacistResponse(_iPharmacistService.mapToResponse(savedComplaint.getPharmacist()));

        return pharmacistResponse;
    }

    @Override
    public ComplaintPharmacyResponse createComplaintForPharmacy(CreateComplaintForPharmacyRequest request) {
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacy(_iPharmacyRepository.findOneById(request.getPharmacyId()));
        complaint.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        Pharmacy pharmacy = _iPharmacyRepository.findOneById(request.getPharmacyId());
        complaint.setText(request.getText());
        Complaint savedComplaint = _iComplaintRepository.save(complaint);
        pharmacy.getComplaints().add(savedComplaint);
        _iPharmacyRepository.save(pharmacy);
        return mapPharmacyToResponse(savedComplaint);
    }

    private ComplaintPharmacyResponse mapPharmacyToResponse(Complaint savedComplaint) {
        ComplaintPharmacyResponse pharmacyResponse = new ComplaintPharmacyResponse();
        pharmacyResponse.setText(savedComplaint.getText());
        pharmacyResponse.setId(savedComplaint.getId());
        pharmacyResponse.setAnswered(savedComplaint.isAnswered());
        pharmacyResponse.setPatientResponse(_iPatientService.mapToResponse(savedComplaint.getPatient()));
        pharmacyResponse.setPharmacyResponse(_iPharmacyService.mapToResponse(savedComplaint.getPharmacy()));

        return pharmacyResponse;
    }

    @Override
    public ComplaintMedicineResponse createComplaintForMedicine(CreateComplaintForMedicineRequest request) {
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacy(_iPharmacyRepository.findOneById(request.getPharmacyId()));
        complaint.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        Medicine medicine = _iMedicineRepository.findOneById(request.getMedicineId());
        complaint.setDermatologist(_iDermatologistRepository.findOneById(request.getMedicineId()));
        complaint.setText(request.getText());
        Complaint savedComplaint = _iComplaintRepository.save(complaint);
        medicine.getComplaints().add(savedComplaint);
        _iMedicineRepository.save(medicine);
        return mapMedicineToResponse(savedComplaint);
    }

    private ComplaintMedicineResponse mapMedicineToResponse(Complaint savedComplaint) {
        ComplaintMedicineResponse medicineResponse = new ComplaintMedicineResponse();
        medicineResponse.setText(savedComplaint.getText());
        medicineResponse.setId(savedComplaint.getId());
        medicineResponse.setAnswered(savedComplaint.isAnswered());
        medicineResponse.setPatientResponse(_iPatientService.mapToResponse(savedComplaint.getPatient()));
        medicineResponse.setMedicineResponse(_iMedicineService.mapToResponse(savedComplaint.getMedicine()));

        return medicineResponse;
    }

    @Override
    public List<ComplaintDermatologistResponse> getAllComplaintsForDermatologist() {
        List<Complaint> complaints = _iComplaintRepository.findAll();
        List<ComplaintDermatologistResponse> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
            if(c.getDermatologist() != null) {
                if (c.isAnswered() == false) {
                    finalComplaints.add(mapDermatologistToResponse(c));
                }
            }
        }
        return finalComplaints;
    }

    @Override
    public List<ComplaintPharmacistResponse> getAllComplaintsForPharmacist() {
        List<Complaint> complaints = _iComplaintRepository.findAll();
        List<ComplaintPharmacistResponse> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
            if(c.getPharmacist() != null) {
                if (c.isAnswered() == false) {
                    finalComplaints.add(mapPharmacistToResponse(c));
                }
            }
        }
        return finalComplaints;
    }

    @Override
    public List<ComplaintPharmacyResponse> getAllComplaintsForPharmacy() {
        List<Complaint> complaints = _iComplaintRepository.findAll();
        List<ComplaintPharmacyResponse> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
            if(c.getPharmacy() != null) {
                if (c.isAnswered() == false) {
                    finalComplaints.add(mapPharmacyToResponse(c));
                }
            }
        }
        return finalComplaints;
    }

    @Override
    public List<ComplaintMedicineResponse> getAllComplaintsForMedicine() {
        List<Complaint> complaints = _iComplaintRepository.findAll();
        List<ComplaintMedicineResponse> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
                if(c.isAnswered() == false){
                    finalComplaints.add(mapMedicineToResponse(c));
                }
        }
        return finalComplaints;
    }

    @Override
    public boolean answerOnComplaint(AnswerOnComplaintRequest request) {
        Complaint complaint = _iComplaintRepository.findOneById(request.getComplaintId());
        complaint.setAnswered(true);
        _iComplaintRepository.save(complaint);

        _iEmailService.answerOnComplaint(request);
        return true;
    }
}

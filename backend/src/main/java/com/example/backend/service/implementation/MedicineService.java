package com.example.backend.service.implementation;

import com.example.backend.dto.request.AllergyRequest;
import com.example.backend.dto.request.CancelMedReservationRequest;
import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.request.ReserveMedicamentRequest;
import com.example.backend.dto.response.MedReservationResponse;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacyMedsResponse;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import com.example.backend.service.IMedicineService;
import com.example.backend.utils.MedShape;
import com.example.backend.utils.MedicamentReservationStatus;
import com.example.backend.utils.MedicineType;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    private final IMedicineRepository _iMedicineRepository;
    private final IPatientRepository _iPatientRepository;
    private final IAllergiesRepository _iAllergiesRepository;
    private final IPharmacyMedsRepository _iPharmacyMedsRepository;
    private final IPharmacyRepository _iPharmacyRepository;
    private final PharmacyService _pharmacyService;
    private final IMedicineReservationRepository _iMedicineReservationRepository;

    public MedicineService(IMedicineRepository iMedicineRepository, IPatientRepository iPatientRepository, IAllergiesRepository iAllergiesRepository, IPharmacyMedsRepository iPharmacyMedsRepository, IPharmacyRepository iPharmacyRepository, PharmacyService pharmacyService, IMedicineReservationRepository iMedicineReservationRepository) {
        _iMedicineRepository = iMedicineRepository;
        _iPatientRepository = iPatientRepository;
        _iAllergiesRepository = iAllergiesRepository;
        _iPharmacyMedsRepository = iPharmacyMedsRepository;
        _iPharmacyRepository = iPharmacyRepository;
        _pharmacyService = pharmacyService;
        _iMedicineReservationRepository = iMedicineReservationRepository;
    }

    @Override
    public MedicineResponse createMed(CreateMedRequest request) {
        Medicine medicine = new Medicine();
        System.out.println(request.getMedicineType());
        if(request.getMedicineType().equals("ANTIBIOTIC")){
            medicine.setMedicineType(MedicineType.ANTIBIOTIC);
        }else if(request.getMedicineType().equals("ANESTHETIC")){
            medicine.setMedicineType(MedicineType.ANESTHETIC);
        }else if(request.getMedicineType().equals("ANTIHISTAMINE")){
            medicine.setMedicineType(MedicineType.ANTIHISTAMINE);
        }else if(request.getMedicineType().equals("ANTIFUNGAL")){
            medicine.setMedicineType(MedicineType.ANTIFUNGAL);
        }else if(request.getMedicineType().equals("ANALGETIC")){
            medicine.setMedicineType(MedicineType.ANALGETIC);
        }

        if(request.getMedShape().equals("POWDER")){
            medicine.setMedShape(MedShape.POWDER);
        }else if(request.getMedShape().equals("CAPSULE")){
            medicine.setMedShape(MedShape.CAPSULE);
        }else if(request.getMedShape().equals("TABLET")){
            medicine.setMedShape(MedShape.TABLET);
        }else if(request.getMedShape().equals("GEL")){
            medicine.setMedShape(MedShape.GEL);
        }else if(request.getMedShape().equals("SYRUP")){
            medicine.setMedShape(MedShape.SYRUP);
        }else if(request.getMedShape().equals("SOLUTION")){
            medicine.setMedShape(MedShape.SOLUTION);
        }


        medicine.setCode(request.getCode());
        medicine.setIngredients(request.getIngredients());
        medicine.setManufacturer(request.getManufacturer());
        medicine.setNotes(request.getNotes());
        medicine.setRecipe(request.isRecipe());
        medicine.setName(request.getName());
        medicine.setReplacementCode(request.getReplacementCode());

        Medicine savedMedicine = _iMedicineRepository.save(medicine);

        return mapToResponse(savedMedicine);
    }

    @Override
    public List<MedicineResponse> getAllMedicaments() {
        List<Medicine> medicaments = _iMedicineRepository.findAll();
        List<MedicineResponse> medicamentResponses = new ArrayList<>();
        for (Medicine medicament: medicaments) {
            MedicineResponse medicamentResponse = mapToResponse(medicament);
            medicamentResponses.add(medicamentResponse);
        }
        return medicamentResponses;
    }

    @Override
    public void addNewAlergy(Long id, AllergyRequest request) {
        Allergies allergy = new Allergies();
        Patient patient = _iPatientRepository.findOneById(id);
        Medicine medicine = _iMedicineRepository.findOneById(request.getId());
        allergy.setPatient(patient);
        allergy.setMedicine(medicine);
        _iAllergiesRepository.save(allergy);
        patient.getAllergies().add(allergy);
        medicine.getAllergies().add(allergy);
        _iMedicineRepository.save(medicine);
        _iPatientRepository.save(patient);
    }

    @Override
    public List<MedicineResponse> getMedicamentPatientIsNotAllergic(Long id) {
        Patient patient = _iPatientRepository.findOneById(id);
        List<MedicineResponse> finalList = new ArrayList<>();
        List<Allergies> allergies = patient.getAllergies();
        List<Medicine> allergicMeds = new ArrayList<>();
        List<Medicine> allMedicaments = _iMedicineRepository.findAll();
        for (Allergies a: allergies){
            allergicMeds.add(a.getMedicine());
        }
        for(Medicine m: allMedicaments){
            if(!allergicMeds.contains(m)){
                finalList.add((mapToResponse(m)));
            }
        }
        return finalList;
    }

    @Override
    public List<PharmacyMedsResponse> getPharmacyMedicines(Long id) {
        //apoteka koja nas zanima
        Pharmacy pharmacy = _iPharmacyRepository.findOneById(id);
        //svi lekovi ikada
        List<PharmacyMeds> pharmacyMeds = _iPharmacyMedsRepository.findAll();


        List<PharmacyMedsResponse> finalResponse = new ArrayList<>();
        //prolazim kroz sve lekove ikada i uporedjujem da li pripadaju mojoj apoteci
        for(PharmacyMeds pm: pharmacyMeds){
            if(pm.getPharmacy().getId().equals(id)){
                finalResponse.add(mapPharmacyMedsToResponse(pm));
            }
        }
        return finalResponse;
    }

    @Override
    public PharmacyMedsResponse reserveMedicament(ReserveMedicamentRequest request, Long id) {
        PharmacyMeds pharmacyMeds = _iPharmacyMedsRepository.findOneById(request.getPharmacyMedId());
        pharmacyMeds.setQuantity(pharmacyMeds.getQuantity() - 1);
        _iPharmacyMedsRepository.save(pharmacyMeds);
        MedicineReservation medicineReservation =  new MedicineReservation();
        medicineReservation.setMedicamentReservationStatus(MedicamentReservationStatus.RESERVED);
        medicineReservation.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        LocalDate date = LocalDate.parse(request.getPickDate());
        medicineReservation.setPickDate(date);
        medicineReservation.setPatient(_iPatientRepository.findOneById(request.getPatientId()));
        medicineReservation.setPharmacyMeds(_iPharmacyMedsRepository.findOneById(request.getPharmacyMedId()));
        _iMedicineReservationRepository.save(medicineReservation);

        return mapPharmacyMedsToResponse(pharmacyMeds);

    }

    @Override
    public List<MedReservationResponse> getReservedActiveMedicines(Long id) {
        List<MedicineReservation> medicineReservations = _iMedicineReservationRepository.findAll();
        List<MedReservationResponse> finalList = new ArrayList<>();
        for(MedicineReservation mr: medicineReservations){
            if(mr.getPatient().getId().equals(id)){
                if(mr.getMedicamentReservationStatus().equals(MedicamentReservationStatus.RESERVED)){
                    finalList.add(mapMedReservationToResponse(mr));
                }

            }
        }
        return finalList;
    }

    @Override
    public boolean cancelMedReservation(CancelMedReservationRequest request) {
        MedicineReservation medicineReservation = _iMedicineReservationRepository.findOneById(request.getId());
        System.out.println(medicineReservation.getId());
        System.out.println("Ovo je id rezervacije koja se otkazuje");

        LocalDate limit = LocalDate.now().minusDays(1);
        System.out.println(limit.toString());
        System.out.println("Ovo je datum do kog se rez moze otkazati");
        if(medicineReservation.getPickDate().isAfter(limit)){
            System.out.println("Upisan datum je pre limita, znaci mogu da ga otkazem");
            medicineReservation.setMedicamentReservationStatus(MedicamentReservationStatus.CANCELED);
            _iMedicineReservationRepository.save(medicineReservation);
            PharmacyMeds pharmacyMeds = _iPharmacyMedsRepository.findOneById(medicineReservation.getPharmacyMeds().getPharmacy().getId());
            System.out.println("vracam lek u apoteku");
            int quantity = pharmacyMeds.getQuantity() + 1;
            System.out.println(quantity);

            pharmacyMeds.setQuantity(quantity);
            _iPharmacyMedsRepository.save(pharmacyMeds);
            return true;
        }else{
            System.out.println("nisam uspeo jer sam posle limita");

            return false;
        }
    }

    @Override
    public List<MedReservationResponse> getReservedDroppedMedicines(Long id) {
        List<MedicineReservation> medicineReservations = _iMedicineReservationRepository.findAll();
        List<MedReservationResponse> finalList = new ArrayList<>();
        for(MedicineReservation mr: medicineReservations){
            if(mr.getPatient().getId().equals(id)){
                if(mr.getMedicamentReservationStatus().equals(MedicamentReservationStatus.DROPPED)){
                    finalList.add(mapMedReservationToResponse(mr));
                }

            }
        }
        return finalList;
    }

    private MedReservationResponse mapMedReservationToResponse(MedicineReservation mr) {
        MedReservationResponse medReservationResponse = new MedReservationResponse();
        medReservationResponse.setPharmacyMedsResponse(mapPharmacyMedsToResponse(mr.getPharmacyMeds()));
        medReservationResponse.setMedicamentReservationStatus(mr.getMedicamentReservationStatus().toString());
        medReservationResponse.setPatientId(mr.getPatient().getId());
        medReservationResponse.setPickDate(mr.getPickDate());
        medReservationResponse.setId(mr.getId());
        return medReservationResponse;
    }

    private PharmacyMedsResponse mapPharmacyMedsToResponse(PharmacyMeds pm) {
        PharmacyMedsResponse pharmacyMedsResponse = new PharmacyMedsResponse();
        pharmacyMedsResponse.setPharmacyResponse(_pharmacyService.mapToResponse(pm.getPharmacy()));
        pharmacyMedsResponse.setMedicineResponse(mapToResponse(pm.getMedicine()));
        pharmacyMedsResponse.setQuantity(pm.getQuantity());
        pharmacyMedsResponse.setId(pm.getId());
        return pharmacyMedsResponse;
    }

    private MedicineResponse mapToResponse(Medicine savedMedicine) {

        MedicineResponse medicineResponse = new MedicineResponse();
        medicineResponse.setMedicineType(savedMedicine.getMedicineType());
        medicineResponse.setId(savedMedicine.getId());
        medicineResponse.setManufacturer(savedMedicine.getManufacturer());
        medicineResponse.setCode(savedMedicine.getCode());
        medicineResponse.setRecipe(savedMedicine.isRecipe());
        medicineResponse.setReplacementCode(savedMedicine.getReplacementCode());
        medicineResponse.setMedShape(savedMedicine.getMedShape());
        medicineResponse.setIngredients(savedMedicine.getIngredients());
        medicineResponse.setNotes(savedMedicine.getNotes());
        medicineResponse.setName(savedMedicine.getName());
        return medicineResponse;
    }
}

package com.example.backend.controller;

import com.example.backend.dto.request.*;
import com.example.backend.dto.response.*;
import com.example.backend.service.IMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
import java.util.List;

@RestController
@RequestMapping("/meds")
public class MedicineController {

    private final IMedicineService _iMedicineService;

    public MedicineController(IMedicineService iMedicineService) {
        _iMedicineService = iMedicineService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public MedicineResponse createMed(@RequestBody CreateMedRequest request) throws Exception {
        return _iMedicineService.createMed(request);
    }

    @GetMapping()
    public List<MedicineResponse> getAllMedicaments(){
        return _iMedicineService.getAllMedicaments();
    }

    @PutMapping("/{id}/alergies")
    public void addNewAlergy(@PathVariable("id")Long id, @RequestBody AllergyRequest request){
        _iMedicineService.addNewAlergy(id, request);

    }

    @PutMapping("/cancel-reservation")
    public boolean cancelMedReservation( @RequestBody CancelMedReservationRequest request){
       return  _iMedicineService.cancelMedReservation(request);

    }

    @GetMapping("/patient/{id}/not/allergic")
    public ResponseEntity<?> getMedicamentPatientIsNotAllergic(@PathVariable("id") Long id){
        List<MedicineResponse> medicineResponses = _iMedicineService.getMedicamentPatientIsNotAllergic(id);
        if(medicineResponses != null) {
            return new ResponseEntity<>(medicineResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getPharmacyMedicines(@PathVariable("id") Long id){
        List<PharmacyMedsResponse> pharmacyMedicines = _iMedicineService.getPharmacyMedicines(id);
        if(pharmacyMedicines != null) {
            return new ResponseEntity<>(pharmacyMedicines, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/reserved-active-medicines")
    public ResponseEntity<?> getReservedActiveMedicines(@PathVariable("id") Long id){
        List<MedReservationResponse> medicineResponses = _iMedicineService.getReservedActiveMedicines(id);
        if(medicineResponses != null) {
            return new ResponseEntity<>(medicineResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/reserved-dropped-medicines")
    public ResponseEntity<?> getReservedDroppedMedicines(@PathVariable("id") Long id){
        List<MedReservationResponse> medicineResponses = _iMedicineService.getReservedDroppedMedicines(id);
        if(medicineResponses != null) {
            return new ResponseEntity<>(medicineResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/order")
    public ResponseEntity<?> getAllMedicinesByOrderId(@PathVariable("id") Long id){
        List<MedicineOrderResponse> responses = _iMedicineService.getAllMedicinesByOrderId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    //rezervacija leka
    @PutMapping("/{id}/pharmacy-medicament")
    public PharmacyMedsResponse reserveMedicament(@RequestBody ReserveMedicamentRequest request, @PathVariable Long id) {
        return _iMedicineService.reserveMedicament(request, id);
    }
}

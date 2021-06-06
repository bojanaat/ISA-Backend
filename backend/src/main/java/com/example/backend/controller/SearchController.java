package com.example.backend.controller;

import com.example.backend.service.ISearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService iSearchService;

    public SearchController(ISearchService iSearchService) {
        this.iSearchService = iSearchService;
    }

    @GetMapping("/medicines")
    public ResponseEntity<?> searchMedicines(@RequestParam("name") String name){
        return new ResponseEntity<>(iSearchService.searchMedicines(name), HttpStatus.OK);
    }

    @GetMapping("/pharmacies")
    public ResponseEntity<?> searchPharmacies(@RequestParam("name") String name, @RequestParam("city") String city){
        return new ResponseEntity<>(iSearchService.searchPharmacies(name, city), HttpStatus.OK);
    }

    @GetMapping("/pharmacy-meds")
    public ResponseEntity<?> searchPharmacyMedicines(@RequestParam("name") String name, @RequestParam("pharmacyId") Long pharmacyId){
        return new ResponseEntity<>(iSearchService.searchPharmacyMedicines(name, pharmacyId), HttpStatus.OK);
    }
}

package com.example.backend.service;

import com.example.backend.dto.response.SearchMedicinesResponse;
import com.example.backend.dto.response.SearchPharmacyMedsResponse;

public interface ISearchService {
    SearchMedicinesResponse searchMedicines(String name);

    SearchPharmacyMedsResponse searchPharmacyMedicines(String name, Long pharmacyId);
}

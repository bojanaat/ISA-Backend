package com.example.backend.repository;

import com.example.backend.model.PharmacyMeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyMedsRepository extends JpaRepository<PharmacyMeds, Long> {
    PharmacyMeds findOneById(Long pharmacyMedId);
}

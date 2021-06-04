package com.example.backend.repository;

import com.example.backend.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IPharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Pharmacy findOneById(Long pharmacyId);

    Set<Pharmacy> findAllByDeleted(boolean b);
}

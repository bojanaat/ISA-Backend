package com.example.backend.repository;

import com.example.backend.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacistRepository extends JpaRepository<Pharmacist, Long> {
}

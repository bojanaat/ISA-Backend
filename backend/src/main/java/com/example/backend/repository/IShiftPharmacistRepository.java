package com.example.backend.repository;

import com.example.backend.model.ShiftDermatologist;
import com.example.backend.model.ShiftPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShiftPharmacistRepository extends JpaRepository<ShiftPharmacist, Long> {
}

package com.example.backend.repository;

import com.example.backend.model.MedicineReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {
    MedicineReservation findOneById(Long id);
}

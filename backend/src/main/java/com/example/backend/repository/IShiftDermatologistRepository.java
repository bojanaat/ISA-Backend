package com.example.backend.repository;

import com.example.backend.model.ShiftDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShiftDermatologistRepository extends JpaRepository<ShiftDermatologist, Long> {
}

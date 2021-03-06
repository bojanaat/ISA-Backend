package com.example.backend.repository;

import com.example.backend.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findOneById(Long id);
}

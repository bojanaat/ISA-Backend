package com.example.backend.repository;

import com.example.backend.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDermatologistRepository extends JpaRepository<Dermatologist, Long> {
    Dermatologist findOneById(Long dermatologistId);
}

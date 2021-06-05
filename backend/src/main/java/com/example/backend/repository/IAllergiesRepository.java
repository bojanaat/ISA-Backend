package com.example.backend.repository;

import com.example.backend.model.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllergiesRepository extends JpaRepository<Allergies, Long> {
}

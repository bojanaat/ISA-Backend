package com.example.backend.repository;

import com.example.backend.model.ExaminationPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExaminationPharmacistRepository extends JpaRepository<ExaminationPharmacist, Long> {
    List<ExaminationPharmacist> findAllByPatient_Id(Long id);

    ExaminationPharmacist findOneById(Long id);
}

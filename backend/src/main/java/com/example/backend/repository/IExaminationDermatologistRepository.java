package com.example.backend.repository;

import com.example.backend.model.ExaminationDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExaminationDermatologistRepository extends JpaRepository<ExaminationDermatologist, Long> {
    List<ExaminationDermatologist> findAllByPharmacy_Id(Long id);

    ExaminationDermatologist findOneById(Long reservationId);

    List<ExaminationDermatologist> findAllByPatient_Id(Long id);
}

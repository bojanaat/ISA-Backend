package com.example.backend.repository;

import com.example.backend.model.Patient;
import com.example.backend.utils.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Patient findOneByUser_Id(Long id);

    Patient findOneById(Long id);

    Set<Patient> findAllByRequestTypeAndUser_Deleted(RequestType approved, boolean b);

    Set<Patient> findAllByRequestType(RequestType pending);
}

package com.example.backend.repository;

import com.example.backend.model.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Long> {
}

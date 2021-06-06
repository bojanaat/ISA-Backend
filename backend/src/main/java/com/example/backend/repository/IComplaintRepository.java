package com.example.backend.repository;

import com.example.backend.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findOneById(Long complaintId);
}

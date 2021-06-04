package com.example.backend.repository;

import com.example.backend.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
    SystemAdmin findOneById(Long id);

    Set<SystemAdmin> findAllByUser_Deleted(boolean b);
}

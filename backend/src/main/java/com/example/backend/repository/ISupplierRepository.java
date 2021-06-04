package com.example.backend.repository;

import com.example.backend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findOneById(Long id);

    Set<Supplier> findAllByUser_Deleted(boolean b);
}

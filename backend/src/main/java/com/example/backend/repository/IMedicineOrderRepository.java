package com.example.backend.repository;

import com.example.backend.dto.response.MedicineOrderResponse;
import com.example.backend.model.MedicineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicineOrderRepository extends JpaRepository<MedicineOrder, Long> {
}

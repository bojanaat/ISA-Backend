package com.example.backend.repository;

import com.example.backend.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActionRepository extends JpaRepository<Action, Long> {
}

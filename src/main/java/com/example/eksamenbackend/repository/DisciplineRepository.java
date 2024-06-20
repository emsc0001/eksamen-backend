package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
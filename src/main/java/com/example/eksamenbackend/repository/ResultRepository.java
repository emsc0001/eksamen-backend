package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByDisciplineIdOrderByResultValueAsc(Long disciplineId);
    List<Result> findByDisciplineIdAndParticipantGenderOrderByResultValueAsc(Long disciplineId, String gender);
    List<Result> findByDisciplineIdAndParticipantAgeBetweenOrderByResultValueAsc(Long disciplineId, int startAge, int endAge);
    List<Result> findByDisciplineIdAndParticipantGenderAndParticipantAgeBetweenOrderByResultValueAsc(Long disciplineId, String gender, int startAge, int endAge);
}
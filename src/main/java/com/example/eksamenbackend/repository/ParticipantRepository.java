package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p WHERE p.name LIKE %:name%")
    List<Participant> searchByName(@Param("name") String name);

    @Query("SELECT p FROM Participant p WHERE (:gender IS NULL OR p.gender = :gender) AND (:club IS NULL OR p.club = :club) AND (:disciplineId IS NULL OR EXISTS (SELECT 1 FROM p.disciplines d WHERE d.id = :disciplineId))")
    List<Participant> filterParticipants(@Param("gender") String gender, @Param("club") String club, @Param("disciplineId") Long disciplineId);
}

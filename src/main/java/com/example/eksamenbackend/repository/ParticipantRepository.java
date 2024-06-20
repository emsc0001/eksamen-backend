package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}

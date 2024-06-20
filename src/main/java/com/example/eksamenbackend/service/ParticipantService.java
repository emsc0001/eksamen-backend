package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = participantRepository.findById(id).orElseThrow();
        participant.setName(participantDetails.getName());
        participant.setGender(participantDetails.getGender());
        participant.setAge(participantDetails.getAge());
        participant.setClub(participantDetails.getClub());
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
    public List<Participant> getParticipantsByAgeGroup(String ageGroup) {
        List<Participant> participants = participantRepository.findAll();
        List<Participant> filteredParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            if (getAgeGroup(participant.getAge()).equals(ageGroup)) {
                filteredParticipants.add(participant);
            }
        }
        return filteredParticipants;
    }

    private String getAgeGroup(int age) {
        if (age >= 6 && age <= 9) return "Children";
        if (age >= 10 && age <= 13) return "Youth";
        if (age >= 14 && age <= 22) return "Junior";
        if (age >= 23 && age <= 40) return "Adult";
        if (age >= 41) return "Senior";
        return "";
    }
}


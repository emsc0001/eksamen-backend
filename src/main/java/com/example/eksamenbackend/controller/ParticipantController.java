package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "http://localhost:5173")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id).orElseThrow();
        return ResponseEntity.ok(participant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participantDetails) {
        Participant updatedParticipant = participantService.updateParticipant(id, participantDetails);
        return ResponseEntity.ok(updatedParticipant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<Participant> filterParticipants(@RequestParam(required = false) String gender,
                                                @RequestParam(required = false) String ageGroup,
                                                @RequestParam(required = false) String club,
                                                @RequestParam(required = false) Long disciplineId) {
        return participantService.filterParticipants(gender, ageGroup, club, disciplineId);
    }

    @GetMapping("/search")
    public List<Participant> searchParticipants(@RequestParam String query) {
        return participantService.searchParticipants(query);
    }
}

package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    public void setup() {
        participantRepository.deleteAll();
    }

    @Test
    public void shouldCreateParticipant() throws Exception {
        mockMvc.perform(post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"gender\": \"Male\", \"age\": 25, \"club\": \"XYZ\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void shouldGetAllParticipants() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        mockMvc.perform(get("/api/participants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void shouldUpdateParticipant() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        mockMvc.perform(put("/api/participants/" + participant.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Jane Doe\", \"gender\": \"Female\", \"age\": 30, \"club\": \"ABC\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void shouldDeleteParticipant() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        mockMvc.perform(delete("/api/participants/" + participant.getId()))
                .andExpect(status().isNoContent());
    }
}

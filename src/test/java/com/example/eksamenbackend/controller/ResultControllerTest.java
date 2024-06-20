package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.enums.ResultType;
import com.example.eksamenbackend.repository.ResultRepository;
import com.example.eksamenbackend.repository.DisciplineRepository;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        resultRepository.deleteAll();
        disciplineRepository.deleteAll();
        participantRepository.deleteAll();
    }

    @Test
    public void shouldGetAllResults() throws Exception {
        mockMvc.perform(get("/api/results"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCreateResult() throws Exception {
        // First create a discipline and participant
        String disciplineJson = "{\"name\":\"100m Sprint\", \"resultType\":\"TIME\"}";
        String participantJson = "{\"name\":\"John Doe\", \"age\":30, \"gender\":\"Male\"}";

        mockMvc.perform(post("/api/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(disciplineJson))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantJson))
                .andExpect(status().isCreated());

        Long disciplineId = disciplineRepository.findAll().get(0).getId();
        Long participantId = participantRepository.findAll().get(0).getId();

        // Now create a result
        String resultJson = String.format(
                "{\"date\":\"%s\", \"resultValue\":\"10.5\", \"discipline\":{\"id\":%d}, \"participant\":{\"id\":%d}, \"resultType\":\"TIME\"}",
                LocalDate.now(), disciplineId, participantId);

        mockMvc.perform(post("/api/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resultJson))
                .andExpect(status().isCreated());
    }
}

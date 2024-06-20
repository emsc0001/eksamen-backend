package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.DisciplineRepository;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.ResultRepository;
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

    private Discipline discipline;
    private Participant participant;

    @BeforeEach
    public void setup() {
        resultRepository.deleteAll();
        disciplineRepository.deleteAll();
        participantRepository.deleteAll();

        discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);
    }

    @Test
    public void shouldCreateResult() throws Exception {
        Result result = new Result();
        result.setResultType("Time");
        result.setDate(LocalDate.of(2024, 6, 20));
        result.setResultValue("10.5");
        result.setDiscipline(discipline);
        result.setParticipant(participant);

        mockMvc.perform(post("/api/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultType").value("Time"))
                .andExpect(jsonPath("$.date").value("2024-06-20"))
                .andExpect(jsonPath("$.resultValue").value("10.5"));
    }

    @Test
    public void shouldGetAllResults() throws Exception {
        Result result = new Result();
        result.setResultType("Time");
        result.setDate(LocalDate.of(2024, 6, 20));
        result.setResultValue("10.5");
        result.setDiscipline(discipline);
        result.setParticipant(participant);
        resultRepository.save(result);

        mockMvc.perform(get("/api/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resultType").value("Time"))
                .andExpect(jsonPath("$[0].date").value("2024-06-20"))
                .andExpect(jsonPath("$[0].resultValue").value("10.5"));
    }

    @Test
    public void shouldUpdateResult() throws Exception {
        Result result = new Result();
        result.setResultType("Time");
        result.setDate(LocalDate.of(2024, 6, 20));
        result.setResultValue("10.5");
        result.setDiscipline(discipline);
        result.setParticipant(participant);
        resultRepository.save(result);

        Result updatedResult = new Result();
        updatedResult.setResultType("Time");
        updatedResult.setDate(LocalDate.of(2024, 6, 21));
        updatedResult.setResultValue("10.6");
        updatedResult.setDiscipline(discipline);
        updatedResult.setParticipant(participant);

        mockMvc.perform(put("/api/results/" + result.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedResult)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultType").value("Time"))
                .andExpect(jsonPath("$.date").value("2024-06-21"))
                .andExpect(jsonPath("$.resultValue").value("10.6"));
    }

    @Test
    public void shouldDeleteResult() throws Exception {
        Result result = new Result();
        result.setResultType("Time");
        result.setDate(LocalDate.of(2024, 6, 20));
        result.setResultValue("10.5");
        result.setDiscipline(discipline);
        result.setParticipant(participant);
        resultRepository.save(result);

        mockMvc.perform(delete("/api/results/" + result.getId()))
                .andExpect(status().isNoContent());
    }
}

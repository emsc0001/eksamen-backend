package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.DisciplineRepository;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.ResultRepository;
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
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @BeforeEach
    public void setup() {
        resultRepository.deleteAll();
        participantRepository.deleteAll();
        disciplineRepository.deleteAll();
    }

    @Test
    public void shouldCreateResult() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        mockMvc.perform(post("/api/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"participant\": {\"id\": " + participant.getId() + "}, \"discipline\": {\"id\": " + discipline.getId() + "}, \"resultType\": \"Time\", \"resultValue\": \"12.34\", \"date\": \"2024-06-20\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value("12.34"));
    }

    @Test
    public void shouldGetAllResults() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        Result result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultType("Time");
        result.setResultValue("12.34");
        result.setDate("2024-06-20");
        resultRepository.save(result);

        mockMvc.perform(get("/api/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resultValue").value("12.34"));
    }

    @Test
    public void shouldUpdateResult() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        Result result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultType("Time");
        result.setResultValue("12.34");
        result.setDate("2024-06-20");
        resultRepository.save(result);

        mockMvc.perform(put("/api/results/" + result.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"participant\": {\"id\": " + participant.getId() + "}, \"discipline\": {\"id\": " + discipline.getId() + "}, \"resultType\": \"Time\", \"resultValue\": \"11.22\", \"date\": \"2024-06-21\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value("11.22"));
    }

    @Test
    public void shouldDeleteResult() throws Exception {
        Participant participant = new Participant();
        participant.setName("John Doe");
        participant.setGender("Male");
        participant.setAge(25);
        participant.setClub("XYZ");
        participantRepository.save(participant);

        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        Result result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultType("Time");
        result.setResultValue("12.34");
        result.setDate("2024-06-20");
        resultRepository.save(result);

        mockMvc.perform(delete("/api/results/" + result.getId()))
                .andExpect(status().isNoContent());
    }
}

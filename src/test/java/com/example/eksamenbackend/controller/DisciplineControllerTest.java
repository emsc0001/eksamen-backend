package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
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
public class DisciplineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @BeforeEach
    public void setup() {
        disciplineRepository.deleteAll();
    }

    @Test
    public void shouldCreateDiscipline() throws Exception {
        mockMvc.perform(post("/api/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"100m\", \"resultType\": \"Time\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("100m"));
    }

    @Test
    public void shouldGetAllDisciplines() throws Exception {
        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        mockMvc.perform(get("/api/disciplines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("100m"));
    }

    @Test
    public void shouldUpdateDiscipline() throws Exception {
        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        mockMvc.perform(put("/api/disciplines/" + discipline.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"200m\", \"resultType\": \"Time\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("200m"));
    }

    @Test
    public void shouldDeleteDiscipline() throws Exception {
        Discipline discipline = new Discipline();
        discipline.setName("100m");
        discipline.setResultType("Time");
        disciplineRepository.save(discipline);

        mockMvc.perform(delete("/api/disciplines/" + discipline.getId()))
                .andExpect(status().isNoContent());
    }
}

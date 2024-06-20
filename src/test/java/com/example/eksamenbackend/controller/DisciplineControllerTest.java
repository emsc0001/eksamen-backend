package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.enums.ResultType;
import com.example.eksamenbackend.service.DisciplineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DisciplineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DisciplineService disciplineService;

    @InjectMocks
    private DisciplineController disciplineController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(disciplineController).build();
    }

    @Test
    public void testGetAllDisciplines() throws Exception {
        Discipline discipline1 = new Discipline();
        discipline1.setId(1L);
        discipline1.setName("Discipline 1");
        discipline1.setResultType(ResultType.TIME);

        Discipline discipline2 = new Discipline();
        discipline2.setId(2L);
        discipline2.setName("Discipline 2");
        discipline2.setResultType(ResultType.DISTANCE);

        List<Discipline> disciplines = Arrays.asList(discipline1, discipline2);

        when(disciplineService.getAllDisciplines()).thenReturn(disciplines);

        mockMvc.perform(get("/api/disciplines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Discipline 1"))
                .andExpect(jsonPath("$[0].resultType").value(ResultType.TIME.name()))
                .andExpect(jsonPath("$[1].name").value("Discipline 2"))
                .andExpect(jsonPath("$[1].resultType").value(ResultType.DISTANCE.name()));

        verify(disciplineService, times(1)).getAllDisciplines();
    }

    @Test
    public void testCreateDiscipline() throws Exception {
        Discipline discipline = new Discipline();
        discipline.setId(1L);
        discipline.setName("New Discipline");
        discipline.setResultType(ResultType.POINTS);

        when(disciplineService.createDiscipline(any(Discipline.class))).thenReturn(discipline);

        mockMvc.perform(post("/api/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(discipline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Discipline"))
                .andExpect(jsonPath("$.resultType").value(ResultType.POINTS.name()));

        verify(disciplineService, times(1)).createDiscipline(any(Discipline.class));
    }
}

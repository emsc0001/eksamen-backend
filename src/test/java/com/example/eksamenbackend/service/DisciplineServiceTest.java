package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.enums.ResultType;
import com.example.eksamenbackend.repository.DisciplineRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DisciplineServiceTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DisciplineService disciplineService;

    public DisciplineServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllDisciplines() {
        disciplineService.getAllDisciplines();
        verify(disciplineRepository, times(1)).findAll();
    }

    @Test
    public void shouldCreateDiscipline() {
        Discipline discipline = new Discipline();
        discipline.setName("100m Sprint");
        discipline.setResultType(ResultType.TIME);
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(discipline);

        Discipline created = disciplineService.createDiscipline(discipline);
        assertNotNull(created);
        assertEquals("100m Sprint", created.getName());
    }

    @Test
    public void shouldThrowExceptionWhenDisciplineNotFound() {
        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            disciplineService.getDisciplineById(1L);
        });
    }
}

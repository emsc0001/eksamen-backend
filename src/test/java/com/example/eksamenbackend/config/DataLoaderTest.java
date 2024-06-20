package com.example.eksamenbackend.config;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DataLoaderTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadDisciplines_whenNoDisciplinesExist() {
        // Arrange
        when(disciplineRepository.count()).thenReturn(0L);

        // Act
        dataLoader.loadDisciplines();

        // Assert
        verify(disciplineRepository, times(1)).count();
        verify(disciplineRepository, times(1)).saveAll(any());
    }

    @Test
    public void testLoadDisciplines_whenDisciplinesAlreadyExist() {
        // Arrange
        when(disciplineRepository.count()).thenReturn(1L);

        // Act
        dataLoader.loadDisciplines();

        // Assert
        verify(disciplineRepository, times(1)).count();
        verify(disciplineRepository, never()).saveAll(any());
    }
}

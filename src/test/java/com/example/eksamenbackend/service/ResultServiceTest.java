package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.entity.Participant;
import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.enums.ResultType;
import com.example.eksamenbackend.repository.DisciplineRepository;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private ResultService resultService;

    private Participant participant;
    private Discipline discipline;
    private Result result;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        participant = new Participant();
        participant.setId(1L);
        participant.setName("John Doe");

        discipline = new Discipline();
        discipline.setId(1L);
        discipline.setName("100m Sprint");
        discipline.setResultType(ResultType.TIME);

        result = new Result();
        result.setId(1L);
        result.setDate(LocalDate.now());
        result.setResultValue("10.5");
        result.setDiscipline(discipline);
        result.setParticipant(participant);
        result.setResultType(ResultType.TIME);
    }

    @Test
    public void testGetAllResults() {
        when(resultRepository.findAll()).thenReturn(Arrays.asList(result));
        List<Result> results = resultService.getAllResults();
        assertEquals(1, results.size());
        assertEquals(result, results.get(0));
    }

    @Test
    public void testGetResultById() {
        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));
        Result foundResult = resultService.getResultById(1L).orElse(null);
        assertEquals(result, foundResult);
    }

    @Test
    public void testCreateResult() {
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        Result createdResult = resultService.createResult(result);
        assertEquals(result, createdResult);
    }

    @Test
    public void testCreateResults() {
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));
        when(resultRepository.saveAll(anyList())).thenReturn(Arrays.asList(result));

        List<Result> createdResults = resultService.createResults(Arrays.asList(result));
        assertEquals(1, createdResults.size());
        assertEquals(result, createdResults.get(0));
    }

    @Test
    public void testUpdateResult() {
        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        Result updatedResult = resultService.updateResult(1L, result);
        assertEquals(result, updatedResult);
    }

    @Test
    public void testDeleteResult() {
        doNothing().when(resultRepository).deleteById(1L);
        resultService.deleteResult(1L);
        verify(resultRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCreateResultParticipantNotFound() {
        when(participantRepository.findById(1L)).thenReturn(Optional.empty());
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            resultService.createResult(result);
        });

        assertEquals("Participant not found", exception.getMessage());
    }

    @Test
    public void testCreateResultDisciplineNotFound() {
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            resultService.createResult(result);
        });

        assertEquals("Discipline not found", exception.getMessage());
    }
}

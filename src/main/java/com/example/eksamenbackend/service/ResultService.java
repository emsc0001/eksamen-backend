package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.DisciplineRepository;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    public Result createResult(Result result) {
        result.setParticipant(participantRepository.findById(result.getParticipant().getId())
                .orElseThrow(() -> new RuntimeException("Participant not found")));
        result.setDiscipline(disciplineRepository.findById(result.getDiscipline().getId())
                .orElseThrow(() -> new RuntimeException("Discipline not found")));

        return resultRepository.save(result);
    }

    public List<Result> createResults(List<Result> results) {
        for (Result result : results) {
            result.setParticipant(participantRepository.findById(result.getParticipant().getId())
                    .orElseThrow(() -> new RuntimeException("Participant not found")));
            result.setDiscipline(disciplineRepository.findById(result.getDiscipline().getId())
                    .orElseThrow(() -> new RuntimeException("Discipline not found")));
        }
        return resultRepository.saveAll(results);
    }

    public Result updateResult(Long id, Result result) {
        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));
        existingResult.setDate(result.getDate());
        existingResult.setResultValue(result.getResultValue());
        existingResult.setDiscipline(disciplineRepository.findById(result.getDiscipline().getId())
                .orElseThrow(() -> new RuntimeException("Discipline not found")));
        existingResult.setParticipant(participantRepository.findById(result.getParticipant().getId())
                .orElseThrow(() -> new RuntimeException("Participant not found")));
        existingResult.setResultType(result.getResultType());
        return resultRepository.save(existingResult);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}

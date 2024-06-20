package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public Result updateResult(Long id, Result resultDetails) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        result.setParticipant(resultDetails.getParticipant());
        result.setDiscipline(resultDetails.getDiscipline());
        result.setResultType(resultDetails.getResultType());
        result.setResultValue(resultDetails.getResultValue());
        result.setDate(resultDetails.getDate());

        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}

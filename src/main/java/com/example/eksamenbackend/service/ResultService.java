package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElseThrow();
    }

    public Result updateResult(Long id, Result resultDetails) {
        Result result = resultRepository.findById(id).orElseThrow();
        result.setResultType(resultDetails.getResultType());
        result.setDate(resultDetails.getDate());
        result.setResultValue(resultDetails.getResultValue());
        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}

package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {
        return resultService.getResultById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));
    }

    @PostMapping
    public List<Result> createResults(@RequestBody List<Result> results) {
        return resultService.createResults(results);
    }

    @PutMapping("/{id}")
    public Result updateResult(@PathVariable Long id, @RequestBody Result result) {
        return resultService.updateResult(id, result);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
    }
}

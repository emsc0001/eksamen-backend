package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Result;
import com.example.eksamenbackend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "http://localhost:5173")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public Optional<Result> getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
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

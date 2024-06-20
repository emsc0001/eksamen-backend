package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
@CrossOrigin(origins = "http://localhost:5173")
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineService.createDiscipline(discipline);
    }

    @GetMapping
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        Discipline discipline = disciplineService.getDisciplineById(id);
        return ResponseEntity.ok(discipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline disciplineDetails) {
        Discipline updatedDiscipline = disciplineService.updateDiscipline(id, disciplineDetails);
        return ResponseEntity.ok(updatedDiscipline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}

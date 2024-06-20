package com.example.eksamenbackend.service;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public Discipline createDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Discipline getDisciplineById(Long id) {
        return disciplineRepository.findById(id).orElseThrow();
    }

    public Discipline updateDiscipline(Long id, Discipline disciplineDetails) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        discipline.setName(disciplineDetails.getName());
        discipline.setResultType(disciplineDetails.getResultType());
        return disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteById(id);
    }
}

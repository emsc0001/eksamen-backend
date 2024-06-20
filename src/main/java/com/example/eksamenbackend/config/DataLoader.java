package com.example.eksamenbackend;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @PostConstruct
    public void loadDisciplines() {
        if (disciplineRepository.count() == 0) {
            Discipline[] disciplines = {
                    new Discipline("1-milløb", "time"),
                    new Discipline("10.000-meterløb", "time"),
                    new Discipline("100-meterløb", "time"),
                    new Discipline("110 meter hækkeløb", "time"),
                    new Discipline("1500-meterløb", "time"),
                    new Discipline("200 meter hækkeløb", "time"),
                    new Discipline("200-meterløb", "time"),
                    new Discipline("3000-meterløb", "time"),
                    new Discipline("4 × 100-meterløb", "time"),
                    new Discipline("4 × 400-meterløb", "time"),
                    new Discipline("400 meter hækkeløb", "time"),
                    new Discipline("400-meterløb", "time"),
                    new Discipline("4x400-meterløb blandet hold", "time"),
                    new Discipline("5000-meter-løb", "time"),
                    new Discipline("60 meter hækkeløb", "time"),
                    new Discipline("60-meterløb", "time"),
                    new Discipline("800-meterløb", "time"),
                    new Discipline("Cross (løbesport)", "time"),
                    new Discipline("Diskoskast", "distance"),
                    new Discipline("Femkamp", "points"),
                    new Discipline("Forhindringsløb", "time"),
                    new Discipline("Halvmaratonløb", "time"),
                    new Discipline("Hammerkast", "distance"),
                    new Discipline("Højdespring", "distance"),
                    new Discipline("Højdespring uden tilløb", "distance"),
                    new Discipline("Kastefemkamp", "points"),
                    new Discipline("Kastetrekamp", "points"),
                    new Discipline("Kuglestød", "distance"),
                    new Discipline("Kørestolsrace", "time"),
                    new Discipline("Længdespring", "distance"),
                    new Discipline("Længdespring uden tilløb", "distance"),
                    new Discipline("Maratonløb", "time"),
                    new Discipline("Slyngboldkast", "distance"),
                    new Discipline("Spydkast", "distance"),
                    new Discipline("Stangspring", "distance"),
                    new Discipline("Syvkamp", "points"),
                    new Discipline("Tikamp", "points"),
                    new Discipline("Tovtrækning", "points"),
                    new Discipline("Trail (løbesport)", "time"),
                    new Discipline("Trespring", "distance"),
                    new Discipline("Trespring uden tilløb", "distance"),
                    new Discipline("Vægtkast", "distance")
            };
            disciplineRepository.saveAll(Arrays.asList(disciplines));
        }
    }
}

package com.example.eksamenbackend.config;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class DataLoader {

    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Autowired
    private DisciplineRepository disciplineRepository;

    @PostConstruct
    public void loadDisciplines() {
        logger.info("Checking if disciplines need to be loaded...");
        if (disciplineRepository.count() == 0) {
            logger.info("Loading initial disciplines...");
            Discipline[] disciplines = {
                    new Discipline("1-milløb", "time"),
                    new Discipline("Diskoskast", "distance"),
                    new Discipline("Femkamp", "points"),
                    // Add more disciplines with appropriate result types
            };
            disciplineRepository.saveAll(Arrays.asList(disciplines));
            logger.info("Initial disciplines loaded successfully.");
        } else {
            logger.info("Disciplines already loaded, skipping initialization.");
        }
    }
}

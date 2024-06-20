package com.example.eksamenbackend.config;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.enums.ResultType;
import com.example.eksamenbackend.repository.DisciplineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
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
            logger.info("No disciplines found. Loading initial disciplines...");

            List<Discipline> disciplines = Arrays.asList(
                    new Discipline("100 meters", ResultType.TIME),
                    new Discipline("200 meters", ResultType.TIME),
                    new Discipline("400 meters", ResultType.TIME),
                    new Discipline("800 meters", ResultType.TIME),
                    new Discipline("1500 meters", ResultType.TIME),
                    new Discipline("5000 meters", ResultType.TIME),
                    new Discipline("10000 meters", ResultType.TIME),
                    new Discipline("Marathon", ResultType.TIME),
                    new Discipline("110 meters hurdles", ResultType.TIME),
                    new Discipline("400 meters hurdles", ResultType.TIME),
                    new Discipline("3000 meters steeplechase", ResultType.TIME),
                    new Discipline("High jump", ResultType.DISTANCE),
                    new Discipline("Pole vault", ResultType.DISTANCE),
                    new Discipline("Long jump", ResultType.DISTANCE),
                    new Discipline("Triple jump", ResultType.DISTANCE),
                    new Discipline("Shot put", ResultType.DISTANCE),
                    new Discipline("Discus throw", ResultType.DISTANCE),
                    new Discipline("Hammer throw", ResultType.DISTANCE),
                    new Discipline("Javelin throw", ResultType.DISTANCE),
                    new Discipline("Decathlon", ResultType.POINTS),
                    new Discipline("Heptathlon", ResultType.POINTS)
            );

            disciplineRepository.saveAll(disciplines);

            logger.info("Initial disciplines loaded successfully.");
        } else {
            logger.info("Disciplines already loaded, skipping initialization.");
        }

        List<Discipline> loadedDisciplines = disciplineRepository.findAll();
        logger.info("Current disciplines in the repository:");
        for (Discipline discipline : loadedDisciplines) {
            logger.info(discipline.getName() + " (" + discipline.getResultType() + ")");
        }
    }
}

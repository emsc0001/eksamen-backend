package com.example.eksamenbackend.config;

import com.example.eksamenbackend.entity.Discipline;
import com.example.eksamenbackend.repository.DisciplineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(DisciplineRepository disciplineRepository) {
        return args -> {
            // Add predefined disciplines
            if (disciplineRepository.count() == 0) {
                disciplineRepository.save(new Discipline("100m Sprint", "Time"));
                disciplineRepository.save(new Discipline("Long Jump", "Distance"));
                disciplineRepository.save(new Discipline("Shot Put", "Distance"));
                disciplineRepository.save(new Discipline("High Jump", "Height"));
                disciplineRepository.save(new Discipline("Marathon", "Time"));
                disciplineRepository.save(new Discipline("Decathlon", "Points"));
            }
        };
    }
}

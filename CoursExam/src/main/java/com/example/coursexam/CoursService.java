package com.example.coursexam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursService {

    private final CoursRepository repository;
    private final StudentClient client;

    public void saveCours(Cours cours) {
        repository.save(cours);
    }

    public List<Cours> findAllCours() {
        return repository.findAll();
    }

    public FullCoursResponse findCoursWithStudents(Integer coursId) {
        var cours = repository.findById(coursId)
                .orElse(
                        Cours.builder()
                                .name("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsByCours(coursId);
        return FullCoursResponse.builder()
                .name(cours.getName())
                .students(students)
                .build();
    }
}

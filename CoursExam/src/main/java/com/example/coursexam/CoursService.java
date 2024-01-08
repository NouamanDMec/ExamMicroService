package com.example.coursexam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursService {

    private final CoursRepository repository;
    private final StudentClient client;

    public void saveSchool(Cours cours) {
        repository.save(cours);
    }

    public List<Cours> findAllSchools() {
        return repository.findAll();
    }

    public FullCoursResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        Cours.builder()
                                .name("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return FullCoursResponse.builder()
                .name(school.getName())
                .students(students)
                .build();
    }
}

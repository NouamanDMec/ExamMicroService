package com.example.coursexam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

    @GetMapping("/cours/{cours-id}")
    List<Student> findAllStudentsByCours(@PathVariable("cours-id") Integer coursId);
}

package org.example.webui.repository;

import org.example.webui.domain.Student;
import org.example.webui.dto.ResponseStudent;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);
    List<Student> findall();
    Optional<Student> findById(Long id);
    Student findByName(String name);
    Student update(Student student);
    void deleteById(Long id);

}

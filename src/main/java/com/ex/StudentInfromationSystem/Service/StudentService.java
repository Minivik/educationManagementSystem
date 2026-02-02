package com.ex.StudentInfromationSystem.Service;

import com.ex.StudentInfromationSystem.Entities.Student;
import com.ex.StudentInfromationSystem.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public Student create(Student s){ return repo.save(s); }
    public List<Student> findAll(){ return repo.findAll(); }
    public void delete(Long id){ repo.deleteById(id); }
}

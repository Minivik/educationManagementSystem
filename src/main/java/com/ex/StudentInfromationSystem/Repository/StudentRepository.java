package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
}
